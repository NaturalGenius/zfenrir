package com.zfenrir.common.common.entity;

/**
 * 
 * @author zhuliang
 * @Date  2021-11-4
 *
 */
public class BasePageForm<T> extends PageParameter{

    T form;

    public T getForm() {
        return form;
    }

    public void setForm(T form) {
        this.form = form;
    }
}
