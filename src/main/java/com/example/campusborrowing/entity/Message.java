package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 消息表实体类
@Data
@TableName("messages")
public class Message {
    @TableId
    private Long Id; // 消息唯一标识
    private Long userId; // 接收者用户ID
    private String content; // 消息内容
    private String type; // 消息类型：system, borrow, return, announcement
    private Boolean isRead; // 是否已读
    private LocalDateTime createdAt; // 创建时间
}
