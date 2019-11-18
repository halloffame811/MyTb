package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.MyTbException.MyTbMvcException;
import com.hzy.mytb.config.AddressConfig;
import com.hzy.mytb.dto.OrderDTO;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.service.impl.OrderMasterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/OrderMasterSeller")
public class OrderMasterSellerController {
    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    @Autowired
    private AddressConfig addressConfig;
    @GetMapping("/orderList")
    public ModelAndView getOrderListByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                           Map<String, Object> map
    ) {
        log.error("卖家获取订单列表");
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> pageResult = orderMasterService.findAllOrders(pageRequest);

        map.put("currentPage", page);
        map.put("size", size);
        map.put("pageResult", pageResult);
        map.put("url", addressConfig.getUrl());
        ModelAndView modelAndView = new ModelAndView("freemarker/order/list", map);
        return modelAndView;
    }

    @GetMapping("/orderDetail")
    public ModelAndView getOrderDetail(@RequestParam(value = "orderId") String orderId,
                                       Map<String, Object> map) {
        log.error("卖家获取订单详情");
        OrderDTO orderDetail = null;
        try {
            orderDetail = orderMasterService.findOne(orderId);
        } catch (MyTbAppException e) {
            throw new MyTbMvcException(ResultVOEnum.SELLER_ORDER__DETAIL_ERROR);
        }
        map.put("orderDetail", orderDetail);
        map.put("url",addressConfig.getUrl());

        ModelAndView modelAndView = new ModelAndView("freemarker/order/detail", map);
        return modelAndView;
    }

    @GetMapping("/orderCancel")
    public ModelAndView orderCancel(@RequestParam(value = "orderId") String orderId,
                                    Map<String, Object> map) {
        log.error("卖家取消订单...");
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDTO);
            //判断结果并提示--->重定向到订单页面
            map.put("msg",ResultVOEnum.SELLER_ORDER_CANCEL_SUCCESS.getMsg());
            map.put("url",addressConfig.getUrl());
            map.put("pageMsg","订单列表页面");
            map.put("returnUrl",addressConfig.getUrl()+"/mytb/OrderMasterSeller/orderList");
            ModelAndView modelAndView = new ModelAndView("freemarker/result/success", map);
            return modelAndView;
        }catch ( MyTbAppException e){
            log.error("卖家取消订单失败原因:"+e.getMessage());
            throw  new MyTbMvcException(ResultVOEnum.SELLER_ORDER_CANCEL_ERROR);
        }
    }
    @GetMapping("/orderFinish")
    public ModelAndView orderFinish(@RequestParam(value = "orderId") String orderId,
                                    Map<String, Object> map) {
        log.error("卖家完结订单...");
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.finish(orderDTO);
            //判断结果并提示--->重定向到订单详情页面
            map.put("msg",ResultVOEnum.SELLER_ORDER_FINISH_SUCCESS.getMsg());
            map.put("url",addressConfig.getUrl());
            map.put("pageMsg","订单列表页面");
            map.put("returnUrl",addressConfig.getUrl()+"/mytb/OrderMasterSeller/orderList");
            ModelAndView modelAndView = new ModelAndView("freemarker/result/success", map);
            return modelAndView;
        }catch ( MyTbAppException e){
            log.error("卖家完结订单失败原因:"+e.getMessage());
            throw  new MyTbMvcException(ResultVOEnum.SELLER_ORDER__FINISH_ERROR);
        }
    }

}
