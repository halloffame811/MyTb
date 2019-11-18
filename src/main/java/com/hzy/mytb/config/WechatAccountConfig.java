package com.hzy.mytb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//微信账号相关的配置
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
    //商户号
    private String mpchId;
    //商户秘钥
    private String mpchKey;
    //商户证书路径
    private String keyPath;
    //微信支付异步通知地址
    private String notifyUrl;
}
