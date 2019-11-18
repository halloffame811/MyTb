package com.hzy.mytb.pojo;

import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    //订单总价
    private BigDecimal orderAmount;
    //订单状态
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态
    private Integer payStatus = PayStatusEnum.PAY_WAIT.getCode();
    private Date createTime;
    private Date updateTime;
}
