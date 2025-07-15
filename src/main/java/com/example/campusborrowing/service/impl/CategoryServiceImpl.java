package com.example.campusborrowing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.campusborrowing.entity.Category;
import com.example.campusborrowing.mapper.CategoryMapper;
import com.example.campusborrowing.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
