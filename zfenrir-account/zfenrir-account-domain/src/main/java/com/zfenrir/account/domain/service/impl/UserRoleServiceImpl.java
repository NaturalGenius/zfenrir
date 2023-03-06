package com.zfenrir.account.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfenrir.account.domain.entity.auto.UserRoleEntity;
import com.zfenrir.account.domain.mapper.auto.UserRoleMapper;
import com.zfenrir.account.domain.service.IUserRoleService;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements IUserRoleService {

}
