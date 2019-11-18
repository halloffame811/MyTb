package com.hzy.mytb.service;

import com.hzy.mytb.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderMasterService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO findOne(String orderId);
    //买家分页查询用户订单列表
    Page<OrderDTO> findPageListByOpenID(String openId, Pageable pageable);
    //卖家分页查询所有用户订单列表
    Page<OrderDTO> findAllOrders(Pageable pageable);

    OrderDTO cancel(OrderDTO orderDTO);
    OrderDTO finish(OrderDTO orderDTO);
    //支付订单
    OrderDTO payed(OrderDTO orderDTO);


}
