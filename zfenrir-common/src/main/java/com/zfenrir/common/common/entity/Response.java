package com.zfenrir.common.common.entity;

import java.io.Serializable;

import com.zfenrir.common.common.interfaces.ResponseCode;
import com.zfenrir.common.exception.ZfenrirException;

public class Response<T> implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应
     */
    private T data;
    
    /**
     * 提示信息
     */
    private String message;
    
    public Response() {}

    public static <T> Response<T> newInstance() {
        return new Response<T>();
    }

    public Response<T> setResultCode(ResponseCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        return this;
    }
    
    public Response<T> setException(ZfenrirException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
        return this;
    }
    
    public Integer getCode() {
        return code;
    }

    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
