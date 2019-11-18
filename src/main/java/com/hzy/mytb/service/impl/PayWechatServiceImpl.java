package com.hzy.mytb.service.impl;

import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.service.PayWechatService;
import com.hzy.mytb.utils.GsonUtils;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayWechatServiceImpl implements PayWechatService {
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Override
    public void pay(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName("点餐助手支付");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
       PayResponse payResponse = bestPayService.pay(payRequest);

       log.error("支付结果："+ GsonUtils.obj2Json(payResponse));
    }
}
