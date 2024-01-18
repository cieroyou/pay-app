package com.sera.payapp.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

//TODO: 고민.. 이거는 money-service 에서 사용하니까, money-service 에서 정의하는게 맞는거 같은데..?

/**
 * 머니 서비스의 머니총전 API 에서 사용하게 되는 Task 로, 요청된 계좌 체크, 멤버쉽 체크 두 개의 SubTask 를 가지고 있다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RechargingMoneyTask {
    private String taskId;
    private String taskName;
    private String membershipId;
    private List<SubTask> subTasks;
    // 충전할 금액을 가져오는 게좌
    private String toBankName;
    // 충전할 금액에 가져오는 계좌번호
    private String toBankAccountNumber;
    private int moneyAmount;

    @Builder
    public RechargingMoneyTask(String taskName, String membershipId, List<SubTask> subTasks, String toBankName, String toBankAccountNumber, int moneyAmount) {
        this.taskId = UUID.randomUUID().toString();
        this.taskName = taskName;
        this.membershipId = membershipId;
        this.subTasks = subTasks;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
    }
}
