package com.hzy.mytb.service.impl;

import com.hzy.mytb.dao.OrderDetailRepository;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.pojo.OrderDetail;
import com.hzy.mytb.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getOrderDetailListByOrder(OrderDTO orderDTO) {

        return  orderDetailRepository.findOrderDetailByOrderId(orderDTO.getOrderId());
    }
}
