package com.zfenrir.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zfenrir.gateway")
public class ZfenrirGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZfenrirGatewayApplication.class, args);
    }
}
