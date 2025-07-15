package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 分类表实体类
@Data
@TableName("category")
public class Category {
    private Integer id;
    private String name;
}