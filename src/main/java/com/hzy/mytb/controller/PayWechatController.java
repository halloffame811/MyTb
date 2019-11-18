package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.service.impl.OrderMasterServiceImpl;
import com.hzy.mytb.service.impl.PayWechatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/Wechatpay")
@Controller
public class PayWechatController {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    @Autowired
    private PayWechatServiceImpl payWechatService;
    @GetMapping("/payOrder")
    public String payByWechat(@RequestParam(value = "orderId")String orderId,
                              @RequestParam(value = "returnUrl")String returnUrl){

        OrderDTO order = orderMasterService.findOne(orderId);

        if (order==null){
            throw  new MyTbAppException(ResultVOEnum.ORDER_NOT_EXIST);
        }
        //TODO
        order.setBuyerOpenid("oHkvbvoZOERPCfDeUZUfocU0tOHg");
        //发起支付
        payWechatService.pay(order);
        return  "";
    }
    @GetMapping("/payNotify")
    public String payNotify(){

       log.error("payNotify...");

        return  "";
    }
}
