package com.sera.payapp.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 여러 서비스에서 특정 membershipid 로 Validation을 수행할 때 사용하는 Task
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SubTask {
    private String membershipId;
    private String subTaskName;
    // TODO; enum 으로 리팩토링 할 것
    private String taskType; // banking, membership
    private String status; // success, fail, ready
}
