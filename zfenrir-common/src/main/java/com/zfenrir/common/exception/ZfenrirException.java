package com.zfenrir.common.exception;

import com.zfenrir.common.common.interfaces.ResponseCode;
import com.zfenrir.common.enums.GlobalResponseCode;

/**
 * Zfenrir 总异常
 * @author zhuliang
 * @Date  2021-10-26
 *
 */
public class ZfenrirException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    
    public ZfenrirException() {
        super(GlobalResponseCode.ERROR.message());
        this.code =GlobalResponseCode.ERROR.code();
        this.message =GlobalResponseCode.ERROR.message();
    }
    public ZfenrirException(ResponseCode responseCode) {
        this(responseCode, responseCode.message());
    }
    public ZfenrirException(ResponseCode responseCode, String message) {
        super(message);
        this.code =responseCode.code();
        this.message =message;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
