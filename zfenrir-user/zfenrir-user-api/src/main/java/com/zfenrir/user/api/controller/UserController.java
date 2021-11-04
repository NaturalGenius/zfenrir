package com.zfenrir.user.api.controller;

import com.zfenrir.common.common.abstracts.ZfenrirBaseController;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;
import com.zfenrir.user.domain.entity.auto.UserEntity;
import com.zfenrir.user.domain.service.IUserService;

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
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX + "/user")
public class UserController extends ZfenrirBaseController<UserEntity, IUserService> {

}
