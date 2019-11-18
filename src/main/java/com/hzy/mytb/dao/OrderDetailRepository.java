package com.hzy.mytb.dao;

import com.hzy.mytb.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findOrderDetailByOrderId(String orderId);
}
