package com.zfenrir.mq.rocket.exception;

public class ZfenrirRocketMqException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ZfenrirRocketMqException() {}

    public ZfenrirRocketMqException(String message) {
        super(message);
    }

    public ZfenrirRocketMqException(Exception e) {
        super(e);
    }

    public ZfenrirRocketMqException(String message, Exception e) {
        super(message, e);
    }

}
