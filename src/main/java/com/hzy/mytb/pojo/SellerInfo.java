package com.hzy.mytb.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class SellerInfo {
    @Id
    private String id;

    private String username;
    private String password;
    private String openid;

    private Date createTime;
    private Date updateTime;

}
