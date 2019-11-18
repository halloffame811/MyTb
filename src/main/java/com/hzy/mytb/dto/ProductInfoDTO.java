package com.hzy.mytb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzy.mytb.mEnum.OrderStatusEnum;
import com.hzy.mytb.mEnum.ProductStatusEnum;
import com.hzy.mytb.utils.EnumUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductInfoDTO {
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

    @JsonIgnore
    //通过code获取对应的枚举
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtils.getEnumByCode(productStatus, ProductStatusEnum.class);
    }
}
