package com.hzy.mytb.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private  String productId;
    private  String productName;
    private BigDecimal productPrice;
    //库存
    private  Integer productStock;
    //状态（上架、下架）
    private  Integer productStatus;
    //所属类目
    private  Integer categoryType;

    private  String productDescription;
    private  String productIcon;
    private Date createTime;
    private  Date updateTime;


}
