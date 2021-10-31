package com.zfenrir.user.domain.util;

import static org.junit.Assert.assertNotNull;

import com.zfenrir.common.common.interfaces.ZfenrirPrincipal;
import com.zfenrir.common.util.EntityHelperUtil;
import com.zfenrir.user.domain.entity.auto.UserEntity;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

import cn.hutool.json.JSONUtil;

public class EntityHelperUtilTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void buildCreateAndUpdateTest() {
        UserEntity userEntity = new UserEntity();
        EntityHelperUtil.buildCreateAndUpdate(userEntity, new ZfenrirPrincipal() {

            @Override
            public String getName() {
                return "test";
            }

            @Override
            public Integer getId() {
                return ThreadLocalRandom.current().nextInt();
            }
        });
        logger.info(JSONUtil.toJsonStr(userEntity));
        assertNotNull(userEntity.getCreateId());
    }

    @Test
    public void buildUpdateTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setCreateId(10);
        EntityHelperUtil.buildCreateAndUpdate(userEntity, new ZfenrirPrincipal() {

            @Override
            public String getName() {
                return "test";
            }

            @Override
            public Integer getId() {
                return ThreadLocalRandom.current().nextInt(0, 1000000);
            }
        });
        logger.info(JSONUtil.toJsonStr(userEntity));
        assertNotNull(userEntity.getUpdateId());
    }
}
