package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbMvcException;
import com.hzy.mytb.config.AddressConfig;
import com.hzy.mytb.config.WechatAccountConfig;
import com.hzy.mytb.dto.BuyerInfoDTO;
import com.hzy.mytb.dto.wechatDTO.AccessTokenResult;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/weixin")
public class WexinController {//原生的方式授权，不建议使用，建议采用SDK的方式，见WechatController
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Autowired
    private AddressConfig addressConfig;
    @GetMapping("/auth")
    public ModelAndView auth(@RequestParam(value = "code") String code,
                             @RequestParam(value = "state") String state){
        log.error("code:"+code);
        log.error("state:"+state);
        Map<String ,Object> map = new HashMap<>();
        try {
        String getAccess_tokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa4692610df651f64&secret=a5fc80796d18a4fca7e58fc46db67441&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
       String getAccess_tokenUrlResult =  restTemplate.getForObject(getAccess_tokenUrl,String.class);
       log.error("获取Access_token返回的结果："+ getAccess_tokenUrlResult);

        AccessTokenResult accessTokenResult = GsonUtils.json2Obj(getAccess_tokenUrlResult,AccessTokenResult.class);
       //拉取用户信息
        String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token="+accessTokenResult.getAccess_token()+"&openid="+accessTokenResult.getOpenid()+"&lang=zh_CN";
      String result =  restTemplate.getForObject(getUserInfoUrl, String.class);

          String userInfoStr = new String(result.getBytes("ISO-8859-1"),"UTF-8");
          BuyerInfoDTO userInfo = GsonUtils.json2Obj(userInfoStr,BuyerInfoDTO.class);
          log.error("获取到的用户信息："+userInfo);
          map.put("userInfo",userInfo);
          ModelAndView modelAndView = new ModelAndView("freemarker/user/userInfo", map);
          return modelAndView;
      }catch (Exception e){
            log.error("拉取用户信息失败！");
            map.put("pageMsg","商品列表");
            map.put("url",addressConfig.getUrl());
            map.put("returnUrl",addressConfig.getUrl()+"/mytb/ProductInfoSeller/productList");
            throw  new MyTbMvcException(ResultVOEnum.WECHAT_OPEN_USER_INFO_ERROR);
      }

    }
}
