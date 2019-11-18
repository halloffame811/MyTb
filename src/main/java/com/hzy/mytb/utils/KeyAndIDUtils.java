package com.hzy.mytb.utils;

import java.util.UUID;

public class KeyAndIDUtils {
    //获取UUID
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
