package com.zfenrir.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.zfenrir.user",
    "com.zfenrir.common"})
public class UserApiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }
}
