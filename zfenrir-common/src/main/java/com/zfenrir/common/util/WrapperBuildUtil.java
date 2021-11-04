package com.zfenrir.common.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Collection;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zfenrir.common.annotation.WrapperBuild;

import cn.hutool.core.util.ReflectUtil;

public class WrapperBuildUtil {

    /**
     * 构建wrapper工具类
     * @param <T>
     * @param <E>
     * @param t
     * @param clazz
     * @return
     */
    public static <T, E> Wrapper<E> buildWrapper(T t, Class<E> clazz){
        if (t== null || clazz == null) {
            return null;
        }
        Field[] fields = ReflectUtil.getFieldsDirectly(t.getClass(), false);
        QueryWrapper<E> wrapper = Wrappers.query();
        for (Field field : fields) {
            WrapperBuild wrapperBuild = field.getAnnotation(WrapperBuild.class);
            if (wrapperBuild == null) {
                continue;
            }
            build(wrapper, wrapperBuild, ReflectUtil.getFieldValue(t, field));
        }
        
        return wrapper;
    }
    
    @SuppressWarnings("rawtypes")
    private static<T> void build(QueryWrapper<T> wrapper, WrapperBuild wrapperBuild, Object value) {
        String columnName = wrapperBuild.columnName();
        boolean con = value != null;
        switch (wrapperBuild.operator()) {
            case EQ:
                wrapper.eq(con, columnName, value);
                break;
            case NE:
                wrapper.ne(con, columnName, value);
                break;
            case GE:
                wrapper.ge(con, columnName, value);
                break;
            case GT:
                wrapper.gt(con, columnName, value);
                break;
            case LE:
                wrapper.le(con, columnName, value);
                break;
            case LT:
                wrapper.lt(con, columnName, value);
            case LIKE:
                wrapper.like(con, columnName, value);
                break;
            case LLIKE:
                wrapper.likeLeft(con, columnName, value);
                break;
            case RLIKE:
                wrapper.likeRight(con, columnName, value);
                break;
            case NOT_LIKE:
                wrapper.notLike(con, columnName, value);
                break;
            case IN:
                wrapper.in(con, columnName, (Collection) value);
                break;
            case NOT_IN:
                wrapper.notIn(con, columnName, (Collection) value);
                break;
            case IS_NOT_NULL:
                wrapper.isNotNull(columnName);
                break;
            case IS_NULL:
                wrapper.isNull(columnName);
                break;
            default:
                throw new IllegalArgumentException(MessageFormat.format("暂不支持的sql类型, column:{0}, sqlType:{1},", columnName, wrapperBuild.operator()));
        }
    }
}