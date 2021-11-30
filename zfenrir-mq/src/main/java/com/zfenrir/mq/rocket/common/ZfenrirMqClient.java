package com.zfenrir.mq.rocket.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZfenrirMqClient {

    /**
     * 消费组
     * @return
     */
    String consumerGroup();
    /**
     * 订阅主题
     * @return
     */
    String topic();
    /**
     * Minimum consumer thread number
     */
    int consumeThreadMin() default 5;
    
    /**
     * Max consumer thread number
     */
    int consumeThreadMax() default 5;
    /**
     * 是否顺序消息
     * @return
     */
    boolean orderly() default false;
    /**
     * tag
     * @return
     */
    String tag() default "*";
    /**
     * 消费模式
     * @return
     */
    ConsumerModel consumerModel() default ConsumerModel.PUSH;
}
