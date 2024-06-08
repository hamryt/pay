package com.daehwapay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private Long membershipId;
    private String subTaskName;
    private String taskType; // banking, membership
    private String status; // success, fail, ready
}
