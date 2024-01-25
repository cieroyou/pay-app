package com.sera.payapp.money.application.service;

import com.sera.payapp.common.CountDownLatchManager;
import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.common.SubTask;
import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.sera.payapp.money.application.port.in.IncreaseMoneyCommand;
import com.sera.payapp.money.application.port.in.IncreaseMoneyUseCase;
import com.sera.payapp.money.application.port.out.IncreaseMoneyChangingRequestPort;
import com.sera.payapp.money.application.port.out.IncreaseMoneyPort;
import com.sera.payapp.money.application.port.out.dto.SendRechargingMoneyTaskPort;
import com.sera.payapp.money.domain.ChangingMoneyStatus;
import com.sera.payapp.money.domain.ChangingMoneyType;
import com.sera.payapp.money.domain.MemberMoney;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UseCase
@Service
@Primary
@RequiredArgsConstructor
public class IncreaseMoneyAsyncService implements IncreaseMoneyUseCase {
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final IncreaseMoneyPort increaseMoneyPort;
    private final IncreaseMoneyChangingRequestPort increaseMoneyChangingRequestPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;
    private final CountDownLatchManager countDownLatchManager;


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
        String taskId = task.getTaskId();
        // 2. kafka 클러스터에 메세지 Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(taskId);

        // task 가 다 끝날때 까지 wait (blocking)
        // task.id 로 매핑한 CountDownLatch 를 이용하여 task 가 다 끝나면 끝나는 쪽에서 countDown() 이 호출되면 여기서에서 await() 이 풀린다.
        try {
            countDownLatchManager.getCountDownLatch(taskId).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3-1. task-consumer: subtask 의 모든 status 가 ok 인지 확인하고 task 결과를 Produce

        // 4. task Result consume
        String taskResult = countDownLatchManager.getDataForKey(taskId);
        if(taskResult.equals("success")) {
            // 5-1. task-result-consumer: task 결과가 success 이면, 충전금액을 증액한다.
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                    new MemberMoney.MembershipId(command.getTargetMembershipId()),
                    command.getAmount()
            );
            if (memberMoneyJpaEntity != null) {
                // MemberMoney 를 성공적으로 증액한 경우
                MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity = increaseMoneyChangingRequestPort.increaseMoney(
                        new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                        new MoneyChangingRequest.MoneyChangingType(ChangingMoneyType.INCREASING),
                        new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                        new MoneyChangingRequest.LinkedStatusIsValid(true),
                        new MoneyChangingRequest.MoneyChangingStatus(ChangingMoneyStatus.SUCCESS),
                        new MoneyChangingRequest.Uuid(UUID.randomUUID()));
                return moneyChangingRequestMapper.mapToDomainEntity(moneyChangingRequestJpaEntity);

            }
        } else {
            // 5-2. task-result-consumer: task 결과가 fail 이면, 충전금액을 증액하지 않는다.
        }
        // 5. consume ok, logic

        return null;

    }

}
