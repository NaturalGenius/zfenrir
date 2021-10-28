package com.zfenrir.common.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.zfenrir.common.common.Response;
import com.zfenrir.common.enums.GlobalResponseCode;
import com.zfenrir.common.exception.ZfenrirException;

import java.util.stream.Collectors;


/**
 * 
 * @author zhuliang
 * @Date  2021-10-27
 *
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("handleMethodArgumentNotValidException", e);
        BindingResult bindingResult = e.getBindingResult();
        return getBindResultErrorResponse(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("handleMissingServletRequestParameterException", e);
        return Response.newInstance().setResultCode(GlobalResponseCode.ERROR).setMessage(e.getMessage());
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<Object> handleMissingServletRequestParameterException(HttpRequestMethodNotSupportedException e) {
        logger.error("handleMissingServletRequestParameterException", e);
        return Response.newInstance().setResultCode(GlobalResponseCode.ERROR).setMessage(e.getMessage());
    }

    /**
     * 请求地址不存在
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
        logger.error("handleNoHandlerFoundException", e);
        return Response.newInstance().setResultCode(GlobalResponseCode.ERROR).setMessage(e.getMessage());
    }

    

    /**
     * 未阈值异常处理
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<Object> handleException(Exception e) {
        logger.error("handleException", e);
        return Response.newInstance().setResultCode(GlobalResponseCode.ERROR);
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(ZfenrirException.class)
    public Response<Object> handleBusinessException(ZfenrirException e) {
        logger.error("handleBusinessException", e);
        return Response.newInstance().setException(e);
    }

    /**
     * 获取参数绑定失败提示信息
     *
     * @param bindingResult
     * @return
     */
    private Response<Object> getBindResultErrorResponse(BindingResult bindingResult){
        if (bindingResult == null || !bindingResult.hasErrors()){
            return Response.newInstance().setResultCode(GlobalResponseCode.ERROR);
        }
        String message = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
        return Response.newInstance().setResultCode(GlobalResponseCode.ERROR).setMessage(message);
    }
}
