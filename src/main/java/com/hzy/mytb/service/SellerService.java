package com.hzy.mytb.service;

import com.hzy.mytb.pojo.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenId(String openId);
}
