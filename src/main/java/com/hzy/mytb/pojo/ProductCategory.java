package com.hzy.mytb.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


/**
 * 商品类目
 */
@Data
@DynamicUpdate
@Entity
public class ProductCategory {
    //类目Id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer categoryId;
    //名称
    private String categoryName;
    //类目编号
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


   // private ArrayList<ProductInfo> productInfos;

}
