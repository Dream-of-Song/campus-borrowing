package com.example.campusborrowing.entity;

import lombok.Data;

@Data
public class BorrowRules {
    private Integer maxBorrowDays;    // 最大借用天数
    private String overduePolicy;     // 逾期政策
    private String damagePolicy;      // 损坏政策
    private String identificationRequired; // 借用所需证件要求
}