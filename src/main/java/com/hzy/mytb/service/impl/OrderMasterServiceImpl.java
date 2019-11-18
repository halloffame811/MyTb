package com.hzy.mytb.service.impl;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.dao.OrderDetailRepository;
import com.hzy.mytb.dao.OrderMasterRepository;
import com.hzy.mytb.dao.ProductInfoRepository;
import com.hzy.mytb.dto.CartDTO;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.PayStatusEnum;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.pojo.OrderDetail;
import com.hzy.mytb.pojo.OrderMaster;
import com.hzy.mytb.pojo.ProductInfo;
import com.hzy.mytb.service.OrderMasterService;
import com.hzy.mytb.utils.OrderMaster2OrderDTOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    //创建订单
    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //生成订单号，姓名，住址，电话
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());

        //计算订单总价
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        for (OrderDetail orderDetail: orderDetailList) {
           Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(orderDetail.getProductId());
                   if (!productInfoOptional.isPresent()) {
                       throw new MyTbAppException(ResultVOEnum.PRODUCT_NOT_EXIST);
                   }
            ProductInfo  productInfo = productInfoOptional.get();

                   BigDecimal price = productInfo.getProductPrice();
                  //计算订单总价
                totalPrice = totalPrice.add( price.multiply(new BigDecimal(orderDetail.getProductQuantity())));

                  //写入订单详情
                    BeanUtils.copyProperties(productInfo,orderDetail);
                  orderDetailRepository.save(orderDetail);
        }
        log.error("总价totalPrice是："+ totalPrice);

        //减库存
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e-> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.cutStock(cartDTOList);

        //设置总价
        orderMaster.setOrderAmount(totalPrice);

        //写入订单数据库
        OrderMaster orderMastResult=orderMasterRepository.save(orderMaster);
        BeanUtils.copyProperties(orderMastResult,orderDTO);
        return  orderDTO;

    }

    //查询单个订单[返回内容包括订单详情]
    @Override
    public OrderDTO findOne(String orderID) {
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderID);
        if (!orderMasterOptional.isPresent()){
            throw new MyTbAppException(ResultVOEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = orderMasterOptional.get();
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailByOrderId(orderID);
        if (orderDetailList.isEmpty()){
            throw new MyTbAppException(ResultVOEnum.ORDER_DETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    //买家分页查询订单列表
    @Override
    public Page<OrderDTO> findPageListByOpenID(String openId, Pageable pageable) {

      Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(openId,pageable);
      List<OrderMaster> pageList= page.getContent();
      List<OrderDTO>  resultPageList;
        //将List<OrderMaster>转换为List<OrderDTO>
       resultPageList = pageList.stream().map(e-> OrderMaster2OrderDTOUtils.orderMaster2OrderDTOUtils(e)).collect(Collectors.toList());
      Page<OrderDTO> result = new PageImpl<>(resultPageList);
        return result;
    }
    //卖家分页查询所有订单列表
    @Override
    public Page<OrderDTO> findAllOrders(Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findAll(pageable);

        List<OrderMaster> pageList= page.getContent();
        //将List<OrderMaster>转换为List<OrderDTO>
        List<OrderDTO>  resultPageList = pageList.stream().map(e-> OrderMaster2OrderDTOUtils.orderMaster2OrderDTOUtils(e)).collect(Collectors.toList());
        Page<OrderDTO> result = new PageImpl<>(resultPageList,pageable,page.getTotalElements());

        return result;
    }

    //取消订单
    @Transactional
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
            Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderDTO.getOrderId());
            if(!orderMasterOptional.isPresent()){
                throw new MyTbAppException(ResultVOEnum.ORDER_CAN_NOT_CANCEL);
            }
            OrderMaster orderMaster = orderMasterOptional.get();
            if(orderMaster.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
                throw new MyTbAppException(ResultVOEnum.ORDER_CAN_NOT_CANCEL);
            }
        //修改订单状态
           orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateStatus =  orderMasterRepository.save(orderMaster);
        if(updateStatus==null){
            throw new MyTbAppException(ResultVOEnum.ORDER_STATUS_UPDATE_ERROR);
        }

        //加库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new MyTbAppException(ResultVOEnum.ORDER_DETAIL_IS_EMPTY);
        }
       List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.addStock(cartDTOList);
        //如果支付了，还需退款
        //TODO
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        OrderDTO order = findOne(orderDTO.getOrderId());
        if (order.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
            throw new MyTbAppException(ResultVOEnum.ORDER_CAN_NOT_FINISH);
        }

        //修改订单
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
       OrderMaster result =  orderMasterRepository.save(orderMaster);
       if (result==null){
           throw new MyTbAppException(ResultVOEnum.ORDER_STATUS_UPDATE_ERROR);
       }
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO payed(OrderDTO orderDTO) {
        //判断订单状态
        OrderDTO order = findOne(orderDTO.getOrderId());
        if (order.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
            throw new MyTbAppException(ResultVOEnum.ORDER_CAN_NOT_PAY);
        }
        //判断支付状态
        if (orderDTO.getPayStatus().equals(PayStatusEnum.PAY_ED))
        {
            throw new MyTbAppException(ResultVOEnum.ORDER_CAN_NOT_PAY);
        }
        //支付
        //TODO
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.PAY_ED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result==null){
            throw new MyTbAppException(ResultVOEnum.ORDER_PAY_ERROR);
        }
        log.error("支付的订单："+result);
        return orderDTO;
    }
}
