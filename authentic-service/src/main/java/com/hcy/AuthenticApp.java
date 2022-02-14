package com.hcy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 16:23
 */
@SpringBootApplication
@MapperScan("com.hcy.mapper")
public class AuthenticApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticApp.class,args);
    }
}
