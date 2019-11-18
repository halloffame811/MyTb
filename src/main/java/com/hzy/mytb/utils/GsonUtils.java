package com.hzy.mytb.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
public class GsonUtils<T>{
    public List<T> jsonArr2List(String jsonArr,Class<T[]> cls){
        Gson gson = new Gson();

        try {
            T[] array = gson.fromJson(jsonArr, cls);
            return Arrays.asList(array);
        }catch (Exception e){
        log.error("json字符串格式不正确：jsonArr="+jsonArr);
        throw  new RuntimeException(e);
        }
    }

    public static <T> T json2Obj(String gsonString, Class<T> cls) {
       Gson gson = new Gson();
         T  t = gson.fromJson(gsonString, cls);
        return t;
    }
    public static String obj2Json(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }

}
