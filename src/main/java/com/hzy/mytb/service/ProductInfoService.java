package com.hzy.mytb.service;

import com.hzy.mytb.dto.CartDTO;
import com.hzy.mytb.dto.ProductInfoDTO;
import com.hzy.mytb.pojo.ProductInfo;
import com.hzy.mytb.vo.ProductCategoryVO;
import com.hzy.mytb.vo.ProductInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOneByProductID(String productID);
    List<ProductInfo> findByProductIdList(List<String> prodcutIDs);
    List<ProductInfo> findByProductStatus(Integer status);
    Page<ProductInfoDTO> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    //查出上架商品，并进行归类，返回带商品数据的类目
    List<ProductCategoryVO> findByProductStatusAndSort (Integer productStatus);

    //根据购物车[订单]加库存
     void addStock(List<CartDTO> cartDTOList);
    //根据购物车[订单]减库存
     void cutStock(List<CartDTO> cartDTOList);

}
