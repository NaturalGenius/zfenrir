package com.zfenrir.common.util;

import com.zfenrir.common.common.interfaces.ZfenrirPrincipal;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import cn.hutool.core.util.ReflectUtil;

/**
 * 为实体构建修改人和创建人
 * 
 * @author zhuliang
 *
 *         2021-10-31
 */
public class EntityHelperUtil {

    private static final String createId = "createId";
    private static final String createName = "createName";
    private static final String createTime = "createTime";
    private static final String updateId = "updateId";
    private static final String updateName = "updateName";
    private static final String updateTime = "updateTime";

    /**
     * 构建实体修改人和创建人
     * 
     * @param <T>
     * @param entity
     * @param principal
     */
    public static <T> void buildCreateAndUpdate(T entity, ZfenrirPrincipal principal) {
        if (entity == null || principal == null) {
            return;
        }
        Object createIdValue = ReflectUtil.getFieldValue(entity, createId);
        if (createIdValue != null) {
            buildUpdate(entity, principal);
            return;
        }
        Field[] fields = ReflectUtil.getFieldsDirectly(entity.getClass(), true);
        for (Field field : fields) {
            String fieldName = field.getName();
            if (Objects.equals(createId, fieldName) || Objects.equals(updateId, fieldName)) {
                ReflectUtil.setFieldValue(entity, field, principal.getId());
            }
            if (Objects.equals(createName, fieldName) || Objects.equals(updateName, fieldName)) {
                ReflectUtil.setFieldValue(entity, field, principal.getName());
            }
            if (Objects.equals(createTime, fieldName) || Objects.equals(updateTime, fieldName)) {
                if (field.getType().isAssignableFrom(LocalDateTime.class)) {
                    ReflectUtil.setFieldValue(entity, field, LocalDateTime.now());
                } else {
                    ReflectUtil.setFieldValue(entity, field, new Date());
                }
            }
        }
    }

    /**
     * 构建修改人
     * 
     * @param <T>
     * @param entity
     * @param principal
     */
    public static <T> void buildUpdate(T entity, ZfenrirPrincipal principal) {
        if (entity == null || principal == null) {
            return;
        }
        Field[] fields = ReflectUtil.getFieldsDirectly(entity.getClass(), true);
        for (Field field : fields) {
            String fieldName = field.getName();
            if (Objects.equals(updateId, fieldName)) {
                ReflectUtil.setFieldValue(entity, field, principal.getId());
            }
            if (Objects.equals(updateName, fieldName)) {
                ReflectUtil.setFieldValue(entity, field, principal.getName());
            }
            if (Objects.equals(updateTime, fieldName)) {
                if (field.getType().isAssignableFrom(LocalDateTime.class)) {
                    ReflectUtil.setFieldValue(entity, field, LocalDateTime.now());
                } else {
                    ReflectUtil.setFieldValue(entity, field, new Date());
                }
            }
        }
    }

}
