package com.zfenrir.util.json;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtil {

    private JsonUtil() {}

    /**
     * 对象转json字符串
     * 
     * @param <T>
     * @param obj
     * @return
     */
    public static <T> String toJsonString(T obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转对象
     * 
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * json字符串转数组
     * 
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

}
