package com.hzy.mytb.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
@DynamicUpdate
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private String productIcon;
    private BigDecimal productPrice;
    //数量
    private  Integer productQuantity;
    private Date createTime;
    private Date updateTime;
}
