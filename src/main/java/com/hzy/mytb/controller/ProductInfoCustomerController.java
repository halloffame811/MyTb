package com.hzy.mytb.controller;

import com.hzy.mytb.mEnum.ProductStatusEnum;
import com.hzy.mytb.service.impl.ProductInfoServiceImpl;
import com.hzy.mytb.utils.ResultVOUtils;
import com.hzy.mytb.vo.ProductCategoryVO;
import com.hzy.mytb.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/ProductInfoCustomer")
@RestController
public class ProductInfoCustomerController {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @RequestMapping("/list")
    public ResultVO getProductsInfo() {
        log.error("ProductInfo---->list请求来了");
            List<ProductCategoryVO> categoryVO= productInfoService.findByProductStatusAndSort(ProductStatusEnum.UP.getCode());
            return  ResultVOUtils.success(categoryVO);
    }
}
