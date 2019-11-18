package com.hzy.mytb.dao;

import com.hzy.mytb.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openID,Pageable pageable);
    //Page<OrderMaster> findAllBy
}
