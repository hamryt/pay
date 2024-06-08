package com.daehwapay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RechargingMoneyTask { // Increase Money

    private String taskId;
    private String taskName;
    private Long membershipId;
    private List<SubTask> subTasks;

    // 법인 계좌
    private String toBankName;

    // 법인 계좌 번호
    private String toBankAccountNumber;

    private int moneyAmount; // only won
}
