package com.sera.payapp.money.application.port.in;

import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.common.SelfValidating;
import com.sera.payapp.common.SubTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyCommand extends SelfValidating<IncreaseMoneyCommand> {
    @NotBlank
    private String targetMembershipId;
    @NotNull
    private int amount;

    public SubTask toValidMemberTask() {
        return SubTask.builder()
                .membershipId(targetMembershipId)
                .subTaskName("validMemberTask: 멤버쉽 유효성 검사")
                .taskType("membership")
                .status("ready")
                .build();
    }

    public SubTask toValidBankAccountTask() {
        return SubTask.builder()
                .membershipId(targetMembershipId)
                .subTaskName("validBankAccountTask: 뱅킹계좌 유효성 검사")
                .taskType("banking")
                .status("ready")
                .build();
    }

    public RechargingMoneyTask toRechargeMoneyTask(List<SubTask> subTasks) {
        return RechargingMoneyTask.builder()
                .taskName("Increase Money Task / 머니 충전 Task")
                .membershipId(targetMembershipId)
                .subTasks(List.of(
                        toValidMemberTask(),
                        toValidBankAccountTask()
                ))
                .toBankName("우리은행")
                .toBankAccountNumber("123-456-789")
                .moneyAmount(amount)
                .build();
    }
}


