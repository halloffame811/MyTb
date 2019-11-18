package com.hzy.mytb.dto.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

    @Data
    public class ProductAddForm {
        @NotEmpty(message = "商品名称不能为空!")
        private  String productName;
        @NotNull(message = "商品价格不能为空!")
        private BigDecimal productPrice;
        //库存
        @NotNull(message = "必须指明商品库存!")
        private  Integer productStock;
        //状态（上架、下架）
        @NotNull(message = "必须为商品指定上下架状态!")
        private  Integer productStatus;
        //所属类目
        @NotNull(message = "商品类目编号不能为空!")
        private  Integer categoryType;

        private  String productDescription;

        @NotEmpty(message = "商品图标地址不能为空!")
        private  String productIcon;
    }


