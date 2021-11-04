package com.zfenrir.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zfenrir.common.enums.ZfenrirSqlOperateEnum;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapperBuild {

    /**
     * 数据库列名
     * 
     * @return
     */
    String columnName();

    /**
     * SQL类型
     * 
     * @return
     */
    ZfenrirSqlOperateEnum operator() default ZfenrirSqlOperateEnum.EQ;
}
