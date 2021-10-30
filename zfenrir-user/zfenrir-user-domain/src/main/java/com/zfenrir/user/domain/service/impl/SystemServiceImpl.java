package com.zfenrir.user.domain.service.impl;

import com.zfenrir.user.domain.entity.auto.System;
import com.zfenrir.user.domain.mapper.auto.SystemMapper;
import com.zfenrir.user.domain.service.ISystemService;
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
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements ISystemService {

}
