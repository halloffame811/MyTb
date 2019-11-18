package com.hzy.mytb.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BuyerInfoDTO {
   private String openid;
   private String nickname;
   private String sex;
   private String province;
   private String city;
   private String country;
   private String headimgurl;
   private ArrayList<String> privilege;
   private String unionid;
}
