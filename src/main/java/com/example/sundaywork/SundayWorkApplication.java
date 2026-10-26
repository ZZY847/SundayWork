package com.example.sundaywork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sundaywork.mapper")
public class SundayWorkApplication {

    public static void main(String[] args) {

        SpringApplication.run(SundayWorkApplication.class, args);
    }

}
