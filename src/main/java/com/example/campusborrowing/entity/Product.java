package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// 物品表实体类
@Data
@TableName("product")
public class Product {
    @TableId
    private Integer id; // 物品唯一标识
    private Integer userId; // 物品发布者ID
    private String name; // 物品名称
    private String description; // 物品描述
    private String department;
    private String productNo;
    private Integer categoryId; // 分类ID
    private Integer quantity; // 可用数量
    private String status; // 物品状态：available, borrowed, maintenance, inactive
    private String location;
    private String rules;
    private BigDecimal price;
//    private Double deposit; // 可选押金

    private String imageUrl; // 可选图片URL
    private LocalDateTime createTime; // 创建时间
}
