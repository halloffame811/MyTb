package com.hzy.mytb.utils;

import com.hzy.mytb.dto.ProductInfoDTO;
import com.hzy.mytb.pojo.ProductInfo;
import org.springframework.beans.BeanUtils;

public class ProductInfo2ProductDTOUtils {
    public static ProductInfoDTO productInfo2productInfoDTOUtils(ProductInfo productInfo){
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfo,productInfoDTO);
        return productInfoDTO;
    }
}
