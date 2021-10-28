package com.zfenrir.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author zhuliang
 * @Date  2021-10-27
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiOperateLog {

    /**
     * 接口说明
     * @return
     */
    String desc() default "";

    /**
     * 是否记录请求日志
     * @return
     */
    boolean writeLog() default true;

    /**
     * 是否记录接口返回值
     * @return
     */
    boolean writeResult() default true;
}
