package com.example.campusborrowing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// 借用记录表实体类
@Data
@TableName("borrow_records")
public class BorrowRecord {
    @TableId
    private Long recordId; // 借用记录唯一标识
    private Long itemId; // 借用物品ID
    private Long borrowerId; // 借用者ID
    private LocalDateTime borrowStart; // 借用开始时间
    private LocalDateTime borrowEnd; // 预计归还时间
    private LocalDateTime returnTime; // 实际归还时间（可为空）
    private String status; // 借用状态：pending, approved, rejected, returned, overdue
    private String comments; // 可选备注
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 最后更新时间
}
