package com.example.campusborrowing.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 公告表实体类
@Data
@TableName("announcements")
public class Announcement {
    @TableId
    private Long announcementId; // 公告唯一标识
    private String title; // 公告标题
    private String content; // 公告内容
    private Long adminId; // 发布管理员ID
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 最后更新时间
}
