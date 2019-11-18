package com.hzy.mytb.mEnum;


import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP("上架",1),
    DOWN("下架",0);

    String name;
    Integer code;
    ProductStatusEnum(String name,Integer integer){
        this.code=integer;
        this.name=name;
    }

}
