package com.zfenrir.core.message;

public interface ParamaterResolve {

    /**
     * 是否支持解析该参数
     * 
     * @param param
     * @return
     */
    boolean support(String param);

    /**
     * 解析参数获取结果
     * 
     * @param param
     * @return
     */
    String resolveParamater(String param);
}
