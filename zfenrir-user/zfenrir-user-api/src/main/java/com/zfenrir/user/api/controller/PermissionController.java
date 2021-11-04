package com.zfenrir.user.api.controller;

import com.zfenrir.common.common.abstracts.ZfenrirBaseController;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;
import com.zfenrir.user.domain.entity.auto.PermissionEntity;
import com.zfenrir.user.domain.service.IPermissionService;

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
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX + "/permission")
public class PermissionController extends ZfenrirBaseController<PermissionEntity, IPermissionService> {

}
