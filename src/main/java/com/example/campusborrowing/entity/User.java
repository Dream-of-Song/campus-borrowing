package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
// 用户表实体类
@Data
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private String phone;
    private LocalDateTime createTime;
}

