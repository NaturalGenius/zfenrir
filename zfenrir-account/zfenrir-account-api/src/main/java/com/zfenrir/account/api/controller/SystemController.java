package com.zfenrir.account.api.controller;

import com.zfenrir.account.api.vo.auth.SystemVO;
import com.zfenrir.account.domain.entity.auto.SystemEntity;
import com.zfenrir.account.domain.service.ISystemService;
import com.zfenrir.common.common.abstracts.ZfenrirBaseController;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;
import com.zfenrir.util.convert.ZfenrirConvertUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX +"/system")
//@Api("系统管理控制器")
public class SystemController extends ZfenrirBaseController<SystemEntity, ISystemService> {

    @Autowired
    private ISystemService systemService;
    
    @GetMapping("/")
  //  @ApiOperation("获取所有的系统列表")
    public List<SystemVO> listAllSystem() {
        return ZfenrirConvertUtil.convertList(systemService.list(), SystemVO.class);
    }
    
}
