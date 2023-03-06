package com.zfenrir.account.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfenrir.account.domain.entity.auto.UserEntity;
import com.zfenrir.account.domain.mapper.auto.UserMapper;
import com.zfenrir.account.domain.service.IUserService;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
