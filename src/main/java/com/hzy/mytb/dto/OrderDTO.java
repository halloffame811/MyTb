package com.hzy.mytb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.PayStatusEnum;
import com.hzy.mytb.pojo.OrderDetail;
import com.hzy.mytb.utils.EnumUtils;
import com.hzy.mytb.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)//如果值为空，则在序列化为json时忽略[已经在yml中全局配置]
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    //订单总价
    private BigDecimal orderAmount;
    //订单状态
    private Integer orderStatus ;
    //支付状态
    private Integer payStatus ;
    @JsonSerialize(using = Date2LongSerializer.class)//去除当前毫秒多余的0
    private Date createTime;
   // @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    //通过code获取对应的枚举
    public  OrderStatusEnum getOrderStatusEnum(){
        return EnumUtils.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public  PayStatusEnum getPayStatusEnum(){
        return EnumUtils.getEnumByCode(payStatus,PayStatusEnum.class);
    }
}
