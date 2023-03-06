package com.zfenrir.account.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfenrir.account.domain.entity.auto.PermissionEntity;
import com.zfenrir.account.domain.mapper.auto.PermissionMapper;
import com.zfenrir.account.domain.service.IPermissionService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuliang
 * @since 2021-10-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {

}
