package com.example.campusborrowing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.campusborrowing.mapper")
@SpringBootApplication
public class CampusBorrowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusBorrowingApplication.class, args);
    }

}
