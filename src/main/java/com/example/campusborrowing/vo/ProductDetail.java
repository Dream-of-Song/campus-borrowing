package com.example.campusborrowing.vo;

import com.example.campusborrowing.entity.BorrowRules;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDetail {
    // 物品基本信息
    private Integer id;
    private String name;
    private String productNo;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String department;
    private String status;
    private String location; // 存放位置
    private Integer categoryId;
    private String categoryName; // 分类名称（前端展示用）
    private Integer userId;
    private LocalDateTime createTime;

    // 借用规则（已解析为对象，无需前端再处理JSON）
    private BorrowRules rules; // 直接使用之前定义的BorrowRules类
}