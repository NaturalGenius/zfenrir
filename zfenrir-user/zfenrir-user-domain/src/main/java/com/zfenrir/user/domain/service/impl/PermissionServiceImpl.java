package com.zfenrir.user.domain.service.impl;

import com.zfenrir.user.domain.entity.auto.Permission;
import com.zfenrir.user.domain.mapper.auto.PermissionMapper;
import com.zfenrir.user.domain.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuliang
 * @since 2021-10-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
