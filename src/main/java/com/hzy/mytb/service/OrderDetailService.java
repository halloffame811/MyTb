package com.hzy.mytb.service;

import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetailListByOrder(OrderDTO orderDTO);
}
