package com.zfenrir.common.common.interfaces;

/**
 * 
 * @author zhuliang
 *
 *         2021-10-30
 */
public interface ZfenrirEnumInterface<T> {

    /**
     * 获取枚举值
     * 
     * @return
     */
    T getValue();

    /**
     * 获取枚举描述
     * 
     * @return
     */
    String desc();
}
