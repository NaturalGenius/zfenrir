package com.zfenrir.account.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zfenrir.account", "com.zfenrir.common"})
@MapperScan("com.zfenrir.account.domain.mapper")
public class AccountApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }
}
