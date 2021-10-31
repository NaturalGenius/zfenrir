package com.zfenrir.user.domain.service.impl;

import com.zfenrir.user.domain.entity.auto.UserEntity;
import com.zfenrir.user.domain.mapper.auto.UserMapper;
import com.zfenrir.user.domain.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
