package com.zfenrir.user.domain.service.impl;

import com.zfenrir.user.domain.entity.auto.Role;
import com.zfenrir.user.domain.mapper.auto.RoleMapper;
import com.zfenrir.user.domain.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
