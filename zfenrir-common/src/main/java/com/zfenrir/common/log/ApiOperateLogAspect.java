package com.zfenrir.common.log;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Sets;
import com.zfenrir.common.annotation.ApiOperateLog;
import com.zfenrir.common.exception.ZfenrirException;

import cn.hutool.json.JSONUtil;

@Component
@Aspect
public class ApiOperateLogAspect {

    /**
     * 打印日志最小执行时间
     */
    @Value("${api.aspect.exec.time.limit:-1}")
    private int execTimeLimit;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Set<LogLevel> printLevelSet = Sets.newHashSet(LogLevel.ERROR, LogLevel.WARN, LogLevel.FATAL);

    @Pointcut("within(com.zfenrir..*Controller)")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void putTraceIdToMdc() {
        MDC.put("trace_id", UUID.randomUUID().toString());
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 开始执行时间
        long startTime = System.currentTimeMillis();
        // 执行
        try {
            Object result = point.proceed();
            // 结束执行时间
            Long execTime = System.currentTimeMillis() - startTime;
            writeLog(LogLevel.INFO, execTime, point, result, null);
            return result;
        } catch (ZfenrirException e) {
            // 业务异常 打印警告
            writeLog(LogLevel.INFO, System.currentTimeMillis() - startTime, point,
                    MessageFormat.format("ZfenrirException:[code:{0}, message:{1}]", e.getCode(), e.getMessage()), null);
            throw e;
        } catch (Exception e){
            // 未能事先预知的异常, 打印错误
            writeLog(LogLevel.ERROR, System.currentTimeMillis() - startTime, point,
                    MessageFormat.format("请求接口执行异常[{0}]", e.getMessage()), e);
            throw e;
        }
    }

    private void writeLog(LogLevel logLevel, long execTime, ProceedingJoinPoint point, Object result, Exception exception) {
        // 大于时间阈值才打印
        if (execTime > execTimeLimit || printLevelSet.contains(logLevel)) {
            Signature signature = point.getSignature();

            MethodSignature methodSignature = (MethodSignature)signature;

            Method targetMethod = methodSignature.getMethod();

            Object[] args = point.getArgs();

            Method realMethod;
            try {
                realMethod = point.getTarget().getClass().getDeclaredMethod(methodSignature.getName(),
                        targetMethod.getParameterTypes());
            } catch (NoSuchMethodException e) {
                realMethod = targetMethod;
            }
            // 判断是否有ApiOperateLog 注解
            if (realMethod.isAnnotationPresent(ApiOperateLog.class)) {
                // 判断是否记录日志
                ApiOperateLog operateLog = realMethod.getAnnotation(ApiOperateLog.class);
                boolean isWriteLog = operateLog.writeLog();
                String desc = operateLog.desc();
                if (isWriteLog){
                    if (operateLog.writeResult()){
                        writeLog(logLevel, execTime, signature, args, desc, result, exception);
                    } else {
                        writeLog(logLevel, execTime, signature, args, desc, "设置不打印接口返回值", exception);
                    }
                }
            } else {
                writeLog(logLevel, execTime, signature, args, null, result, exception);
            }
        }
    }

    /**
     * 写日志
     *
     * @param execTime 执行时间
     * @param signature
     * @param args
     * @param desc
     */
    private void writeLog(LogLevel logLevel, long execTime, Signature signature, Object[] args, String desc, Object result, Exception exception) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String argJson = "";
        if (args != null) {
            try {
                argJson = JSONUtil.toJsonStr(args);
            } catch (Exception e) {
                argJson = Arrays.toString(args);
            }
        }
        ApiOperateLogModel operateLogModel = new ApiOperateLogModel(null, request.getRequestURL().toString(),
                request.getRemoteAddr(), signature.getDeclaringTypeName() + "." + signature.getName(),
                argJson, execTime , desc, JSONUtil.toJsonStr(result));
        String logJsonStr = JSONUtil.toJsonStr(operateLogModel);
        switch (logLevel) {
            case INFO: logger.info(logJsonStr); break;
            case WARN: logger.warn(logJsonStr); break;
            case TRACE:
            case DEBUG: logger.debug(logJsonStr); break;
            case FATAL:
            case OFF:
            case ERROR: logger.error(logJsonStr, exception); break;
        }
    }
}
