package com.zfenrir.common.log;

import java.io.Serializable;

/**
 * 
 * @author zhuliang
 * @Date  2021-10-27
 *
 */
public class ApiOperateLogModel implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作主题
     */
    private Object principal;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 类名方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private String args;

    /**
     * 执行时间
     */
    private Long executeTime;

    /**
     * 自定义接口描述
     */
    private String desc;

    /**
     * 返回值
     */
    private String result;

    public ApiOperateLogModel(Object principal, String url, String ip, String classMethod,
        String args, Long executeTime, String desc, String result) {
        this.principal = principal;
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
        this.executeTime = executeTime;
        this.desc = desc;
        this.result = result;
    }
    public ApiOperateLogModel() {
    }
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
