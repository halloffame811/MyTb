package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.MyTbException.MyTbMvcException;
import com.hzy.mytb.config.AddressConfig;
import com.hzy.mytb.dto.ProductInfoDTO;
import com.hzy.mytb.dto.from.ProductAddForm;
import com.hzy.mytb.dto.from.ProductUpdateForm;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.pojo.ProductCategory;
import com.hzy.mytb.pojo.ProductInfo;
import com.hzy.mytb.service.impl.ProductCategoryServiceImpl;
import com.hzy.mytb.service.impl.ProductInfoServiceImpl;
import com.hzy.mytb.utils.KeyAndIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ProductInfoSeller")
public class ProductInfoSellerController {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Autowired
    private AddressConfig addressConfig;
    @GetMapping("/productList")
    public ModelAndView getProductInfoListByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "8") Integer size, Map<String, Object> map){

        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<ProductInfoDTO> pageResult  =  productInfoService.findAll(pageRequest);
        map.put("currentPage",page);
        map.put("size",size);

        map.put("url",addressConfig.getUrl());
        map.put("pageResult",pageResult);
        log.error("结果："+pageResult.getContent());
        ModelAndView modelAndView = new ModelAndView("freemarker/product/list", map);
        return modelAndView;
    }
    @GetMapping("/findOneForUpdate")
    public ModelAndView findOneForUpdate(@RequestParam String productId){
        log.error("findOneForUpdate....");
        try {
            ProductInfo productInfo = productInfoService.findOneByProductID(productId);
            List<ProductCategory> productCategories = productCategoryService.findAll();

            Map<String ,Object> map = new HashMap<>();
            map.put("url",addressConfig.getUrl());
            map.put("product",productInfo);
            map.put("categoryType",productCategories);
            ModelAndView modelAndView = new ModelAndView("freemarker/product/update", map);
            return modelAndView;
        } catch (MyTbAppException e) {
            throw  new MyTbMvcException(ResultVOEnum.PRODUCT_NOT_EXIST);
        }

    }

    @GetMapping("/productAdd")
    public ModelAndView addProduct(@Valid ProductAddForm form,
                                   BindingResult bindingResult){
        log.error("productAdd...."+form);
        if ( bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("添加商品错误原因："+msg);
            throw new MyTbMvcException(ResultVOEnum.PARAMS_ERROR);
        }
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form,productInfo);
        String productId = KeyAndIDUtils.getUUid();
        productInfo.setProductId(productId);

            ProductInfo result = productInfoService.save(productInfo);
            if (result==null){
                throw new MyTbMvcException(ResultVOEnum.PRODUCT_ADD_ERROR);
            }
            Map<String ,Object> map = new HashMap<>();
            map.put("msg",ResultVOEnum.PRODUCT_ADD_SUCCESS);
            map.put("pageMsg","商品列表");
            map.put("url",addressConfig.getUrl());
            map.put("returnUrl",addressConfig.getUrl()+"/mytb/ProductInfoSeller/productList");
            log.error("新增["+productInfo.getProductName()+"]商品成功");
            ModelAndView modelAndView = new ModelAndView("freemarker/result/success", map);
            return modelAndView;

    }
    @GetMapping("/requestAddProduct")
    public ModelAndView requestAddProduct(){
        log.error("requestAddProduct....");
        //TODO 授权验证身份
        log.error("请求添加商品信息身份验证成功，前往添加页面。");
        List<ProductCategory> productCategories = productCategoryService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("url",addressConfig.getUrl());
        map.put("productCategories",productCategories);
        ModelAndView modelAndView = new ModelAndView("freemarker/product/add", map);
        return modelAndView;
    }
    @GetMapping("/productUpdate")
    public ModelAndView productUpdate( ProductUpdateForm form,
                                   BindingResult bindingResult
    ){
        log.error("productUpdate...."+form);
        if ( bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("修改商品错误原因："+msg);
            throw new MyTbMvcException(ResultVOEnum.PARAMS_ERROR);
        }

        try {
            ProductInfo productInfo = productInfoService.findOneByProductID(form.getProductId());
            BeanUtils.copyProperties(form,productInfo);
            ProductInfo result = productInfoService.save(productInfo);
            if (result==null){
                throw new MyTbMvcException(ResultVOEnum.PRODUCT_UPDATE_ERROR);
            }
            Map<String ,Object> map = new HashMap<>();
            map.put("url",addressConfig.getUrl());
            map.put("msg",ResultVOEnum.PRODUCT_UPDATE_SUCCESS);
            map.put("pageMsg","商品列表");
            map.put("returnUrl",addressConfig.getUrl()+"/mytb/ProductInfoSeller/productList");
            log.error("商品信息更新成功");
            ModelAndView modelAndView = new ModelAndView("freemarker/result/success", map);
            return modelAndView;
        } catch (MyTbAppException e) {
            throw  new MyTbMvcException(ResultVOEnum.PRODUCT_NOT_EXIST);
        }

    }
    @GetMapping("/productUpdateStatus")
    public ModelAndView productUpdateStatus(@RequestParam(value = "productId") String productId,
                                        @RequestParam(value = "productStatus" ) Integer status, Map<String, Object> map){
        log.error("更新商品上下架状态");
            ProductInfo product = productInfoService.findOneByProductID(productId);
            product.setProductStatus(status);
            ProductInfo updateResult =productInfoService.save(product);
            if (updateResult==null) {
                throw new MyTbMvcException(ResultVOEnum.PRODUCT_STATUS_UPDATE_ERROR);
            }
        map.put("msg",ResultVOEnum.PRODUCT_STATUS_UPDATE_SUCCESS.getMsg());
        map.put("url",addressConfig.getUrl());
        map.put("pageMsg","订单列表页面");
        map.put("returnUrl",addressConfig.getUrl()+"/mytb/ProductInfoSeller/productList");
        ModelAndView modelAndView = new ModelAndView("freemarker/result/success", map);
        return modelAndView;
    }
}
