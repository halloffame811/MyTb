package com.hzy.mytb.service;

import com.hzy.mytb.dto.OrderDTO;

public interface PayWechatService {
    void pay(OrderDTO orderDTO);
}
