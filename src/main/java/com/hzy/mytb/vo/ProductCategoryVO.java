package com.hzy.mytb.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryId;
    @JsonProperty("products")
    private List<ProductInfoVO> productInfoVOs;
}
