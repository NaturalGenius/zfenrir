package com.zfenrir.account.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfenrir.account.domain.entity.auto.SystemEntity;
import com.zfenrir.account.domain.mapper.auto.SystemMapper;
import com.zfenrir.account.domain.service.ISystemService;

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
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemEntity> implements ISystemService {

}
