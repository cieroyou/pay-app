package com.sera.payapp.money.application.service;

import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.common.SubTask;
import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.application.port.in.IncreaseMoneyCommand;
import com.sera.payapp.money.application.port.in.IncreaseMoneyUseCase;
import com.sera.payapp.money.application.port.out.dto.SendRechargingMoneyTaskPort;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@UseCase
@Service
@Primary
@RequiredArgsConstructor
public class IncreaseMoneyAsyncService implements IncreaseMoneyUseCase {
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;


    /**
     * 카프카를 이용하여 비동기로 증액 요청을 처리하는 서비스
     * 멤버쉽 유효성 체크, 게좌(충전금액을 빼내는) 유효성 체크를 REST API 가 아니라 카프카를 이용하여 비동기로 메세지를 발생하여 처리한다.
     */
    @Override
    public MoneyChangingRequest increaseMoney(IncreaseMoneyCommand command) {
        // 1-1. subTask: 멤버쉽 유효성 체크
        SubTask validMemberTask = command.toValidMemberTask();
        // 1-2. subTask: 게좌(충전금액을 빼내는) 유효성 체크
        SubTask validBankAccountTask = command.toValidBankAccountTask();
        // TODO: 1-3 subTask: 충전할 금액에 대한 유효성 체크

        List<SubTask> subTasks = Arrays.asList(validMemberTask, validBankAccountTask);
        RechargingMoneyTask task = command.toRechargeMoneyTask(subTasks);

        // 2. kafka 클러스터에 메세지 Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);

        // 3-1. task-consumer: subtask 의 모든 status 가 ok 인지 확인하고 task 결과를 Produce

        // 4. task Result consume
        // 5. consume ok, logic

        return null;

    }

}
