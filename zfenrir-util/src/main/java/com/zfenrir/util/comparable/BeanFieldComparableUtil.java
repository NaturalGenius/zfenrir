package com.zfenrir.util.comparable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.zfenrir.util.comparable.ComparableResult.ComparableFiledDetail;

import cn.hutool.core.util.ReflectUtil;

/**
 * 对象的属性比较
 * @author zhuliang
 * @Date  2021-10-29
 *
 */
public class BeanFieldComparableUtil {

    /**
     * 对象里边属性值比较
     * @param <T>
     * @param sourceObj 源对象
     * @param targerObj 目标对象
     * @return
     */
    public static <T> ComparableResult compare(T sourceObj, T targerObj) {
        return compare(sourceObj, targerObj, null);
    }
    
    /**
     * 对象里边属性值比较
     * @param <T>
     * @param sourceObj 源对象
     * @param targerObj 目标对象
     * @param ignoreFields 忽略的属性
     * @return
     */
    public static <T> ComparableResult compare(T sourceObj, T targerObj, Set<String> ignoreFields) {
        return compare(sourceObj, targerObj, ignoreFields, true);
    }
    
    /**
     * 对象里边属性值比较
     * @param <T>
     * @param sourceObj 源对象
     * @param targerObj 目标对象
     * @param ignoreFields 忽略的属性
     * @param withSuper 是否包含父类属性
     * @return
     */
    public static <T> ComparableResult compare(T sourceObj, T targerObj ,Set<String> ignoreFields, boolean withSuper) {
        return compare(sourceObj, targerObj, ignoreFields, withSuper, false);
    }
    
    /**
     * 对象里边属性值比较
     * @param <T>
     * @param sourceObj 源对象
     * @param targerObj 目标对象
     * @param ignoreFields 忽略的属性
     * @param withSuper 是否包含父类属性
     * @param ignoreTargetNull 忽略目标对象的空置
     * @return
     */
    public static <T> ComparableResult compare(T sourceObj, T targerObj ,Set<String> ignoreFields, boolean withSuper, boolean ignoreTargetNull) {
        ComparableResult comparableResult = new ComparableResult(true);
        if (sourceObj == targerObj) {
            return comparableResult;
        }
        List<ComparableFiledDetail> details = new ArrayList<>();
        Field[] fieldsDirectly = ReflectUtil.getFieldsDirectly(sourceObj == null ? targerObj.getClass() : sourceObj.getClass(), withSuper);
        for (Field field : fieldsDirectly) {
            String fieldName = field.getName();
            if (ignoreFields != null && ignoreFields.contains(fieldName)) {
                continue;
            }
            Object newFiledVale = ReflectUtil.getFieldValue(targerObj, field);
            if (ignoreTargetNull && newFiledVale == null) {
                continue;
            }
            Object oldFiledVale = ReflectUtil.getFieldValue(sourceObj, field);
            if (!Objects.equals(oldFiledVale, newFiledVale)) {
                details.add(new ComparableFiledDetail(fieldName, oldFiledVale, newFiledVale));
            }
        }
        comparableResult.setEquals(details.isEmpty());
        comparableResult.setFiledDetails(details);
        return comparableResult;
    }
    
}
