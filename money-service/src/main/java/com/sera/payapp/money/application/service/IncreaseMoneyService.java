package com.sera.payapp.money.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.sera.payapp.money.application.port.in.IncreaseMoneyCommand;
import com.sera.payapp.money.application.port.in.IncreaseMoneyUseCase;
import com.sera.payapp.money.application.port.out.IncreaseMoneyChangingRequestPort;
import com.sera.payapp.money.application.port.out.IncreaseMoneyPort;
import com.sera.payapp.money.domain.ChangingMoneyStatus;
import com.sera.payapp.money.domain.ChangingMoneyType;
import com.sera.payapp.money.domain.MemberMoney;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@UseCase
@Service
@RequiredArgsConstructor
public class IncreaseMoneyService implements IncreaseMoneyUseCase {
    private final IncreaseMoneyChangingRequestPort increaseMoneyChangingRequestPort;
    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;


    @Override
    public MoneyChangingRequest increaseMoney(IncreaseMoneyCommand command) {
        // 1. 고객 정보가 정상인지 확인
        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액에 충분한지도 확인
        // 3. 법인 계좌 상태도 정상인지 화깅ㄴ
        // 4. 증액을 위한 기농. 요청 상태로 MoneyChangingRequest를 생성
        // 5. 펌뱅킹 수행하고 (고객의 연동된 계좌 -> 페이 법인 계좌)
        // 6-1. 성공하면 MoneyChangingRequest 의 상태를 성공으로 변경, 멤버의 MemberMoney 의 값 증액
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
        return null;


        // 6-2. 실패하면 MoneyChangingRequest의 상태를 실패로 변경


    }

}
