package com.zfenrir.util.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 枚举工具类
 * @author zhuliang
 * @Date  2021-10-26
 *
 */
public class AnnotationUtil {

    private AnnotationUtil() {}

    /**
     * 修改注解的值
     * 
     * @param object 类
     * @param annotation 注解
     * @param filedName 属性名
     * @param filedValue 新的属性值
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void modifyFiled(Object object, Class<? extends Annotation> annotation, String filedName,
        Object filedValue) throws Exception {
        Annotation anno = object.getClass().getAnnotation(annotation);
        InvocationHandler h = Proxy.getInvocationHandler(anno);
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field hField = h.getClass().getDeclaredField("memberValues");
        // 因为这个字段事 private final 修饰，所以要打开权限
        hField.setAccessible(true);
        // 获取 memberValues
        Map memberValues = (Map)hField.get(h);
        // 修改 value 属性值
        memberValues.put(filedName, filedValue);
    }
}
