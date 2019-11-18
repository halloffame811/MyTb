package com.hzy.mytb.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
    @JsonProperty("name")
    private String productName;
    @JsonProperty("id")
    private String productId;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("icon")
    private String productIcon;
    private String productDescription;
}
