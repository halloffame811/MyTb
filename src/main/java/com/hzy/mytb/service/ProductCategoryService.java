package com.hzy.mytb.service;

import com.hzy.mytb.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
   ProductCategory findOne(Integer categoryId);
   List<ProductCategory> findAll();
    ProductCategory save(ProductCategory productCategory);

}
