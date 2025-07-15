package com.example.campusborrowing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("ping")
    public String sayHello() {
        return "Hello World!";
    }
}
