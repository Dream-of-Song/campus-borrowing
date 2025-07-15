package com.example.campusborrowing.controller;

import com.example.campusborrowing.common.Result;
import com.example.campusborrowing.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<?> List(){
        return Result.success(categoryService.list());
    }
}
