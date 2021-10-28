package com.zfenrir.common.enums;

import com.zfenrir.common.common.ResponseCode;

public enum GlobalResponseCode implements ResponseCode {
    SUCCESS(200, "OK"),
    ERROR(500, "error"),
    ;

    private Integer code;
    private String message;
    
    private GlobalResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
