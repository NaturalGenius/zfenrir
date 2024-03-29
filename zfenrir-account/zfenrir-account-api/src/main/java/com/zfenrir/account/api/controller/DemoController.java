package com.zfenrir.account.api.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfenrir.account.api.vo.DemoVO;
import com.zfenrir.common.annotation.ResponseHandleNo;
import com.zfenrir.common.constant.ZfenrirUrlPrefixConstant;

@RestController
@RequestMapping(ZfenrirUrlPrefixConstant.USER_PREFIX + "/demo")
//@Api("Demo")
public class DemoController {

    @GetMapping("/test")
 //   @ApiOperation("demo接口-1")
    public String demoTest() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000));
    }
    
    @GetMapping("/test2")
    @ResponseHandleNo
  //  @ApiOperation("demo接口-2")
    public String demoTest2() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000));
    }
    @GetMapping("/test3")
    public DemoVO demoTest3() {
        return new DemoVO();
    }
}
