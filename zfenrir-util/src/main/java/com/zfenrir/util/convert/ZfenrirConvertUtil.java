package com.zfenrir.util.convert;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.zfenrir.util.json.JsonUtil;

import cn.hutool.core.convert.Convert;

/**
 * 对象转换工具类
 * @author zhuliang
 * @Date  2021-11-1
 *
 */
public class ZfenrirConvertUtil extends Convert{

    /**
     * list对象转换
     * @param <E>
     * @param <T>
     * @param source
     * @param target
     * @return
     */
    public static <E, T> List<T> convertList(Collection<E> collection, Class<T> clazz) {
        if (CollectionUtils.isEmpty(collection) || clazz == null) {
            return Lists.newArrayList();
        }
        return JsonUtil.parseArray(JsonUtil.toJsonString(collection), clazz);
    };
   
    /**
     * 单个对象准换
     * @param <T>
     * @param source
     * @param target
     * @return
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        if (obj == null || clazz == null) {
            return null;
        }
        return JsonUtil.parseObject(JsonUtil.toJsonString(obj), clazz);
    };
}
