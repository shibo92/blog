package com.shibo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shibo
 */
@EnableTransactionManagement // 启动注解事务，等同于传统Spring 项目中xml配置<tx:annotation-driven />
@SpringBootApplication
@EnableFeignClients
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
