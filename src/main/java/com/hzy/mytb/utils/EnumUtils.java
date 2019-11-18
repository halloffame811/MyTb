package com.hzy.mytb.utils;

import com.hzy.mytb.mEnum.CodeEnum;

public class EnumUtils {
    public  static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> cls){
        for (T t:cls.getEnumConstants()) {
            if (code.equals(t.getCode())){
                return  t;
            }
        }
        return null;
    }
}
