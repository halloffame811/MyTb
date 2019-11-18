package com.hzy.mytb.mEnum;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
 PAY_ED("已支付",1),
 PAY_WAIT("未支付",0)
;
    private String name;
    private Integer code;

    PayStatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
