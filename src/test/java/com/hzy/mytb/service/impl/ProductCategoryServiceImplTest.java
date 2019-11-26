package com.hzy.mytb.service.impl;

import com.hzy.mytb.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(100);
      log.error("结果："+productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = productCategoryService.findAll();
        log.error("list:"+list);
    }

    @Test
    public void save() {
    }
}