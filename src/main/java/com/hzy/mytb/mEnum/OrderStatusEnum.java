package com.hzy.mytb.mEnum;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {


    NEW("新订单",1),
    CANCEL("已取消",2),
    FINISHED("完结",3)
    ;
    public String name;
    private Integer code;

    OrderStatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
