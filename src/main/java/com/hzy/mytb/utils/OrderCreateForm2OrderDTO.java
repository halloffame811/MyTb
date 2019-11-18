package com.hzy.mytb.utils;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.dto.from.OrderCreateForm;
import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.PayStatusEnum;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.pojo.OrderDetail;

import java.util.List;

public class OrderCreateForm2OrderDTO {
    public  static OrderDTO orderCreateForm2OrderDTO(OrderCreateForm form){
        OrderDTO orderDTO = new OrderDTO();
        //订单号
        String orderId= KeyAndIDUtils.getUUid();
        orderDTO.setOrderId(orderId);

        orderDTO.setBuyerPhone(form.getPhone());
        orderDTO.setBuyerName(form.getName());
        orderDTO.setBuyerAddress(form.getAddress());
        orderDTO.setBuyerOpenid(form.getOpenid());

        orderDTO.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());

        String jsonArr= form.getItems();

        GsonUtils gsonUtils = new GsonUtils<OrderDetail>();
        try {
            List<OrderDetail> orderDetailList = gsonUtils.jsonArr2List(jsonArr,OrderDetail[].class);
            for (OrderDetail orderDetail:orderDetailList) {
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyAndIDUtils.getUUid());
            }
            orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }catch (Exception e){
            throw new MyTbAppException(ResultVOEnum.PARAMS_ERROR);
        }

    }
}
