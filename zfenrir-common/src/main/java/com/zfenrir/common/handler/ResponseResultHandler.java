package com.zfenrir.common.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zfenrir.common.annotation.ResponseHandleNo;
import com.zfenrir.common.common.entity.Response;
import com.zfenrir.common.enums.GlobalResponseCode;
import com.zfenrir.util.annotation.AnnotationUtil;


@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object>, InitializingBean {

    @Value("${response.result.handler.base.packages:}")
    private String basePackages;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ResponseHandleNo responseHandleNo = returnType.getMethod().getDeclaringClass().getAnnotation(ResponseHandleNo.class);
        if (responseHandleNo == null) {
            responseHandleNo = returnType.getMethodAnnotation(ResponseHandleNo.class);
        }
        return responseHandleNo == null || !responseHandleNo.handleNo();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        if (body instanceof Response) {
            return body;
        }
        return Response.newInstance().setResultCode(GlobalResponseCode.SUCCESS).setData(body);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!ObjectUtils.isEmpty(basePackages)) {
            AnnotationUtil.modifyFiled(this, ControllerAdvice.class, "basePackages", basePackages.split(","));
        }
    }

}
