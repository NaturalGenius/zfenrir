package com.zfenrir.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 请求地址不存在处理
 * @author zhuliang
 * @Date  2021-10-27
 *
 */
@RestController
public class UrlNotFoundHandler implements ErrorController {

    @RequestMapping
    public void error(HttpServletResponse resp, HttpServletRequest req) throws NoHandlerFoundException {
        // 请求地址不存在处理
        throw new NoHandlerFoundException(req.getMethod(), req.getServletPath(), new HttpHeaders());
    }
}
