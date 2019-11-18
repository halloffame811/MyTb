package com.hzy.mytb.service.impl;

import com.hzy.mytb.dao.SellerInfoRepository;
import com.hzy.mytb.pojo.SellerInfo;
import com.hzy.mytb.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return sellerInfoRepository.findSellerInfoByOpenid(openId);
    }
}
