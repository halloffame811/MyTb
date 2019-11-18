package com.hzy.mytb.controller;

import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.service.impl.OrderDetailServiceImpl;
import com.hzy.mytb.service.impl.OrderMasterServiceImpl;
import com.hzy.mytb.utils.ResultVOUtils;
import com.hzy.mytb.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/OrderDetailCustomer")
public class OrderDetailCustomerController {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    @GetMapping("/orderDetailList")
    public ResultVO orderDetailList (@RequestParam(value = "openid") String openid,
                                     @RequestParam (value = "orderId")String orderId
                                     ){
            if (StringUtils.isEmpty(openid)||StringUtils.isEmpty( orderId)){
                return ResultVOUtils.error(ResultVOEnum.PARAMS_ERROR);
            }
             //TODO 判断该openId对应的用户是否下过此订单，下过才允许查询

            OrderDTO result = orderMasterService.findOne(orderId);
           return ResultVOUtils.success(result);

    }
}
