package com.hzy.mytb.controller;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.mEnum.ResultVOEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;
    @GetMapping("/auth")
    public String getWechatAuth(@RequestParam("returnUrl") String returnUrl ){
        log.error("auth.....");
        String url ="http://mytb.nat300.top/mytb/wechat/userInfo";
       String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.error("redirectUrl:"+redirectUrl);
        return  "redirect:"+ redirectUrl;
    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code")String code,
                         @RequestParam("state")String returnUrl){
        log.error("userInfo.....");
        WxMpOAuth2AccessToken accessToken;
        try {
          accessToken =  wxMpService.oauth2getAccessToken(code);
          String openId=accessToken.getOpenId();

          log.error("openId为："+openId);
          return  "redirect:"+ returnUrl+"?openId="+ openId;
        } catch (WxErrorException e) {
            String msg= e.getError().getErrorMsg();
            log.error("获取AccessToken失败....."+msg);
            throw  new MyTbAppException(msg,ResultVOEnum.WECHAT_MP_ERROR.getCode());
        }
    }

}
