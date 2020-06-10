package com.gy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.gy.system.annotation.EnableRyFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@MapperScan("com.gy.*.mapper")
@EnableRyFeignClients
public class GyGenApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(GyGenApp.class, args);
    }
}
