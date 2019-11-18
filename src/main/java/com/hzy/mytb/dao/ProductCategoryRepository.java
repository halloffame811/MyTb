package com.hzy.mytb.dao;

import com.hzy.mytb.pojo.ProductCategory;
import com.hzy.mytb.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository <ProductCategory,Integer>{
 /*   @Query(value="select new com.hzy.mytb.pojo.ProductCategory(pc.categoryId,pc.categoryName,pc.categoryType) from ProductCategory pc,ProductInfo pi where pc.categoryType = pi.categoryType")
    List<ProductCategory> findProductCategoryByCategoryId();*/
    List<ProductCategory> findProductCategoriesByCategoryTypeIn(List<Integer> categoryTypes);
}
