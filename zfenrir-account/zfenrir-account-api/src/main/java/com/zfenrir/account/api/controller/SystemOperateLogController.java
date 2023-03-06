package com.zfenrir.account.api.controller;

import com.zfenrir.account.domain.entity.auto.SystemOperateLogEntity;
import com.zfenrir.account.domain.service.ISystemOperateLogService;
import com.zfenrir.common.common.abstracts.ZfenrirBaseController;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhuliang
 * @since 2021-10-31
 */
@RestController
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX + "/systemOperateLog")
public class SystemOperateLogController extends ZfenrirBaseController<SystemOperateLogEntity, ISystemOperateLogService> {

}
