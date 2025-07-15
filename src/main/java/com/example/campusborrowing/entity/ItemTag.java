package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 物品标签关联表实体类
@Data
@TableName("item_tags")
public class ItemTag {
    private Long itemId; // 物品ID
    private Long tagId; // 标签 ID
    // 注：复合主键(itemId, tagId)需要额外配置，MyBatis-Plus默认不支持复合主键
}
