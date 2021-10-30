package com.zfenrir.common.enums.auth;

import com.zfenrir.common.common.interfaces.ZfenrirEnumInterface;

/**
 * 角色类型枚举
 * 
 * @author zhuliang
 *
 *         2021-10-30
 */
public enum RoleTypeEnum implements ZfenrirEnumInterface<Integer> {
    NORMAL(1, "普通角色"), SYSTEM(2, "系统角色"), SYSTEM_ADMIN(3, "系统管理员"), SUPER_ADMIN(4, "超级管理员")

    ;

    private int value;
    private String desc;

    private RoleTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String desc() {
        return this.desc;
    }

}
