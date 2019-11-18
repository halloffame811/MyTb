package com.hzy.mytb.dao;

import com.hzy.mytb.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void getProductCategoryInf(){
      Integer id =1;
      Optional<ProductCategory> op = productCategoryRepository.findById(id);
        ProductCategory p = op.get();
        log.error(p.toString());
    }
    @Test
    public void saveProductCategory(){
        ProductCategory pc = productCategoryRepository.findById(1).get();
      pc.setCategoryId(1);
        pc.setCategoryName("川笑菜");
        pc.setCategoryType(1);
        ProductCategory result = productCategoryRepository.save(pc);
        log.error("保存的对象:"+ result);
    }

}