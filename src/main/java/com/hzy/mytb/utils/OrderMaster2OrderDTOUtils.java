package com.hzy.mytb.utils;

import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

public class OrderMaster2OrderDTOUtils {
    public static OrderDTO orderMaster2OrderDTOUtils(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
}
