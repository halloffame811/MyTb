package com.hzy.mytb.dao;

import com.hzy.mytb.mEnum.ProductStatusEnum;
import com.hzy.mytb.pojo.ProductInfo;
import com.hzy.mytb.utils.KeyAndIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {
@Autowired
ProductInfoRepository productInfoRepository;
    @Test

    public void save(){
        ProductInfo productInfo = new ProductInfo();
        String imgUrl ="" +
             "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570821902249&di=9f094d6bb16b91baa17de874af2dad1d&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170103%2F561b808cd9e94b6e97185fa0e7241153_th.jpeg"
                ;
        String productId = KeyAndIDUtils.getUUid();
        log.error("productId长度："+productId.length());
        productInfo.setProductId(productId);
        productInfo.setCategoryType(3);
        productInfo.setProductName("老麻抄手");
        productInfo.setProductPrice(new BigDecimal(0.13));
        productInfo.setProductDescription("麻到你喊妈！");
        productInfo.setProductIcon(imgUrl);
        productInfo.setProductStock(222);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
     ProductInfo result = productInfoRepository.save(productInfo);
     log.error("存进去的值："+result);
    }
}