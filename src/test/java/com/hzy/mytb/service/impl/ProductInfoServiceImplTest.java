package com.hzy.mytb.service.impl;

import com.hzy.mytb.dao.ProductInfoRepository;
import com.hzy.mytb.mEnum.ProductStatusEnum;
import com.hzy.mytb.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void findOneByProductID() {
        String productId ="CA12EEC5C714406B9241B49A06CBFD8B";
          ProductInfo product = productInfoRepository.findById(productId).get();
          log.error("查询到的商品："+ product);

    }

    @Test
    public void findByProductID() {
    }

    @Test
    public void findByProductStatus() {
        Integer status = ProductStatusEnum.UP.getCode();
        List<ProductInfo> products = productInfoRepository.findByProductStatus(status);
        log.error("上架的商品："+products);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,1);
      Page<ProductInfo> page =  productInfoRepository.findAll(pageRequest);
      List<ProductInfo> products = page.getContent();
      log.error("分页得到的数据："+ products);
    }

    @Test
    public void save() {
    }
}