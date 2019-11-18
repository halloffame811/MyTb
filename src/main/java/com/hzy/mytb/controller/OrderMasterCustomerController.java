package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.dto.from.OrderCreateForm;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.service.impl.OrderDetailServiceImpl;
import com.hzy.mytb.service.impl.OrderMasterServiceImpl;
import com.hzy.mytb.utils.OrderCreateForm2OrderDTO;
import com.hzy.mytb.utils.ResultVOUtils;
import com.hzy.mytb.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/OrderMasterCustomer")
public class OrderMasterCustomerController {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    //创建订单
    @PostMapping("/createOrder")
    public ResultVO createOrder(@Valid OrderCreateForm form, BindingResult bindingResult ){
        log.error("来了一个创建订单请求！");
        if (bindingResult.hasErrors()){
            log.error("表单参数不合法!");
            String msg = ":"+ bindingResult.getFieldError().getDefaultMessage();
            return ResultVOUtils.error(ResultVOEnum.PARAMS_ERROR.getMsg() + msg,ResultVOEnum.PARAMS_ERROR.getCode());
        }
        //根据表单中的数据生成订单DTO
        OrderDTO orderDTO = OrderCreateForm2OrderDTO.orderCreateForm2OrderDTO(form);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
          throw  new MyTbAppException(ResultVOEnum.CART_CAN_NOT_EMPTY);
        }

           //创建订单
           OrderDTO result =  orderMasterService.create(orderDTO);
           //返还订单号
           HashMap<String,String>resultV0= new HashMap<>();
           resultV0.put("orderId",result.getOrderId());
           return   ResultVOUtils.success(resultV0);

    }


    //分页查询订单列表
    @GetMapping("/orderList")
    public ResultVO orderList(@RequestParam(value = "openid") String openId,
                              @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                              @RequestParam(value = "size",defaultValue = "10") Integer size) {
        log.error("来了一个查询订单列表请求！");
        //openId为必传参数
        if (StringUtils.isEmpty(openId)) {
            return ResultVOUtils.error(ResultVOEnum.PARAMS_ERROR.getMsg() + ":openid不可为空", ResultVOEnum.PARAMS_ERROR.getCode());
        }
        PageRequest pageRequest = PageRequest.of(page - 1, size);
            Page<OrderDTO> pageResult = orderMasterService.findPageListByOpenID(openId, pageRequest);
            List<OrderDTO> result = pageResult.getContent();
                return ResultVOUtils.success(result);
    }

    @PostMapping("/cancelOrder")
    public ResultVO cancelOrder(@RequestParam(value = "openid") String openId,
                                @RequestParam(value = "orderId") String orderId
                                ) {
        if (StringUtils.isEmpty(orderId)){
            throw  new MyTbAppException(ResultVOEnum.PARAMS_ERROR);
        }
        OrderDTO result;
            //构建OrderDTO，准备商品详情列表List数据
            OrderDTO orderDTO =orderMasterService.findOne(orderId);

            if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
                throw  new MyTbAppException(ResultVOEnum.ORDER_AND_OPENID_NOT_MATE);
            }
            result = orderMasterService.cancel(orderDTO);
            return ResultVOUtils.success(result);

    }
}
