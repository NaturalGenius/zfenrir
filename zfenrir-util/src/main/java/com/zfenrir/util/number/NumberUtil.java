package com.zfenrir.util.number;

public class NumberUtil extends cn.hutool.core.util.NumberUtil{

    private NumberUtil() {
    }
    
    /**
     * 将null准换为0
     * @param numner
     * @return
     */
    public static int null2Zero(Integer numner) {
        return numner == null ? 0 : numner;
    }
}
