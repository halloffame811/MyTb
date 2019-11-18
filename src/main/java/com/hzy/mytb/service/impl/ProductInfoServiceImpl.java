package com.hzy.mytb.service.impl;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.MyTbException.MyTbMvcException;
import com.hzy.mytb.dao.ProductCategoryRepository;
import com.hzy.mytb.dao.ProductInfoRepository;
import com.hzy.mytb.dto.CartDTO;
import com.hzy.mytb.dto.ProductInfoDTO;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.pojo.ProductCategory;
import com.hzy.mytb.pojo.ProductInfo;
import com.hzy.mytb.service.ProductInfoService;
import com.hzy.mytb.utils.ProductInfo2ProductDTOUtils;
import com.hzy.mytb.vo.ProductCategoryVO;
import com.hzy.mytb.vo.ProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductInfo findOneByProductID(String productID) {
      Optional<ProductInfo> product =  productInfoRepository.findById(productID);
      if (!product.isPresent()){
          throw new MyTbAppException(ResultVOEnum.PRODUCT_NOT_EXIST);
      }
        ProductInfo productInfo = product.get();
        return  productInfo;
    }

    @Override
    public List<ProductInfo> findByProductIdList(List<String> productIDs) {
        return productInfoRepository.findAllById(productIDs);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer status) {
        return productInfoRepository.findByProductStatus(status);
    }

    @Override
    public Page<ProductInfoDTO> findAll(Pageable pageable) {
       Page<ProductInfo> productInfos =  productInfoRepository.findAll(pageable);
       if (productInfos==null || CollectionUtils.isEmpty(productInfos.getContent())){
           throw  new MyTbMvcException(ResultVOEnum.PRODUCT_NOT_EXIST);

       }
       List<ProductInfoDTO> productInfoDTOs =productInfos.getContent().stream().map(e-> ProductInfo2ProductDTOUtils.productInfo2productInfoDTOUtils(e)).collect(Collectors.toList());
        Page<ProductInfoDTO> result = new PageImpl<>(productInfoDTOs,pageable,productInfos.getTotalElements());
        return result;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Transactional
    @Override
    public List<ProductCategoryVO> findByProductStatusAndSort(Integer productStatus) {
        //查询上架商品
       List<ProductInfo> productList =  findByProductStatus(productStatus);
       //查询所有类目
      // List<ProductCategory> categoryList =  productCategoryRepository.findAll();

      List<Integer> categoryTypes =
               productList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
       //查询有商品的类目
       List<ProductCategory> categoryList =  productCategoryRepository.findProductCategoriesByCategoryTypeIn(categoryTypes);

        List<ProductCategoryVO> productCategoryVOS = new ArrayList<>();
        for (ProductCategory pc: categoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            ArrayList<ProductInfoVO> productInfoVOS = new ArrayList<>();
            BeanUtils.copyProperties(pc,productCategoryVO);
            for (ProductInfo pi:productList) {
                if (pi.getCategoryType().intValue()==pc.getCategoryType().intValue()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(pi,productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productCategoryVO.setProductInfoVOs(productInfoVOS);
            productCategoryVOS.add(productCategoryVO);
        }
        return productCategoryVOS;
    }

    @Transactional
    @Override
    public void addStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()){
                throw new MyTbAppException(ResultVOEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo info = productInfo.get();
            info.setProductStock(info.getProductStock()+cartDTO.getProductQuantity());
            ProductInfo result = productInfoRepository.save(info);
            if (result==null){
                throw new MyTbAppException(ResultVOEnum.PRODUCT_STOCK_UPDATE_ERROR);
            }
        }
    }

    @Transactional
    @Override
    public void cutStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()){
                throw new MyTbAppException(ResultVOEnum.PRODUCT_NOT_EXIST);
            }
            if (productInfo.get().getProductStock()<cartDTO.getProductQuantity()){
                throw new MyTbAppException(ResultVOEnum.PRODUCT_STOCK_NOT_ENOUGH);
            }
            ProductInfo info = productInfo.get();

            info.setProductStock(info.getProductStock() - cartDTO.getProductQuantity());
          ProductInfo result = productInfoRepository.save(info);
            if (result==null){
                throw new MyTbAppException(ResultVOEnum.PRODUCT_STOCK_UPDATE_ERROR);
            }
        }
    }
}
