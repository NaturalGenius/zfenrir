package com.zfenrir.user.api.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfenrir.common.annotation.ResponseHandleNo;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX + "/demo")
@Api("Demo")
public class DemoController {

    @GetMapping("/test")
    @ApiOperation("demo接口-1")
    public String demoTest() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000));
    }
    
    @GetMapping("/test2")
    @ResponseHandleNo
    @ApiOperation("demo接口-2")
    public String demoTest2() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000));
    }
}
