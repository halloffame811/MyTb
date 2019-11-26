package com.hzy.mytb.service.impl;

import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.PayStatusEnum;
import com.hzy.mytb.pojo.OrderDetail;
import com.hzy.mytb.utils.KeyAndIDUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@SpringBootTest
@RunWith(SpringRunner.class
)
public class OrderMasterServiceImplTest {

@Autowired
private  OrderMasterServiceImpl orderMasterService;
@Autowired
private OrderDetailServiceImpl orderDetailService;
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        //订单号
        String orderId= KeyAndIDUtils.getUUid();
        orderDTO.setOrderId(orderId);

        orderDTO.setBuyerName("雨雨雨");
        orderDTO.setBuyerAddress("某街道某栋某楼");
        orderDTO.setBuyerOpenid("freestyle_hzy3");
        orderDTO.setBuyerPhone("13678128732");
        orderDTO.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail_1 = new OrderDetail();
        orderDetail_1.setDetailId(KeyAndIDUtils.getUUid());
        orderDetail_1.setOrderId(orderId);
        orderDetail_1.setProductId("CA12EEC5C714406B9241B49A06CBFD8B");//橙汁196
        orderDetail_1.setProductQuantity(1);
        orderDetailList.add(orderDetail_1);

        OrderDetail orderDetail_2 = new OrderDetail();
        orderDetail_2.setDetailId(KeyAndIDUtils.getUUid());
        orderDetail_2.setOrderId(orderId);
        orderDetail_2.setProductId("CEDF6B2D7B5346F0A1BA49E77D2C9AAA");//蛋炒饭 258
        orderDetail_2.setProductQuantity(4);
        orderDetailList.add(orderDetail_2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderMasterService.create(orderDTO);
        log.error("新建的订单为："+result);

    }

    @Test
    public void findOne() {
        String orderId="01379F1823FA44599A06EF469E4CFA1A";
       OrderDTO result=  orderMasterService.findOne(orderId);
       log.error("查询到的订单："+result);
    }

    @Test
    public void findPageListByOpenID() {
        String openId= "freestyle_hzy2";
        Integer page = 0;
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<OrderDTO> result = orderMasterService.findPageListByOpenID(openId,pageRequest);
        log.error("查询"+(++page)+"页的结果："+result.getContent());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("5FE5B00DFE37469D93D2CD8449CB0361");
       List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailListByOrder(orderDTO);
       orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderMasterService.cancel(orderDTO);
        log.error("取消订单结果："+result);
    }

    @Test
    public void finish() {
        String orderId="DF7F296C67E948499A10245A562A2AD9";
        OrderDTO orderDTO=  orderMasterService.findOne(orderId);
        log.error("要取消的订单："+orderDTO);
        OrderDTO result = orderMasterService.finish(orderDTO);
        log.error("完结的的订单："+result);
    }

    @Test
    public void payed() {
        String orderId="DF7F296C67E948499A10245A562A2AD9";
        OrderDTO orderDTO=  orderMasterService.findOne(orderId);
        log.error("要支付的订单："+orderDTO);
       OrderDTO result = orderMasterService.payed(orderDTO);
       log.error("订单支付结果："+result);
    }
}