package com.sera.payapp.remittance.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.sera.payapp.remittance.application.port.in.RequestRemittanceUseCase;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import com.sera.payapp.remittance.application.port.out.MembershipPort;
import com.sera.payapp.remittance.application.port.out.MoneyPort;
import com.sera.payapp.remittance.application.port.out.RequestRemittancePort;
import com.sera.payapp.remittance.application.port.out.dto.MembershipStatus;
import com.sera.payapp.remittance.application.port.out.dto.MoneyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@UseCase
@Service
@RequiredArgsConstructor
public class RequestRemittanceService implements RequestRemittanceUseCase {
    private final MembershipPort membershipPort;

    private final MoneyPort moneyPort;
    private final RequestRemittancePort requestRemittancePort;

    @Override
    public RequestRemittanceInfo requestRemittance(RequestRemittanceCommand command) {
        // 0. 송금 요청 상태를 시작 상태로 기록
        RemittanceRequestJpaEntity entity = requestRemittancePort.createRemittanceRequestHistory(command);

        // 1. 고객 정보가 정상인지 확인(to membership-service)
        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
        if (!membershipStatus.isValid()) {
            // TODO: null 대신 에러 상태를 반환하도록 함
            return null;
        }
        // 2. 잔액 확인(to money-service)
        MoneyInfo moneyInfo = moneyPort.getMoneyInfo(command.getFromMembershipId());
        if (moneyInfo.getBalance() < command.getAmount()) {
            // TODO: 잔액이 충분치 않은 경우, 만원 단위로 충전하기
            int rechargeAmount = (int) Math.ceil((command.getAmount() - moneyInfo.getBalance()) / 10000.0) * 10000;
            // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)
            boolean moneyResult = moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount);
            if (!moneyResult) {
                return null;
            }

        }
        // 2-1. 잔액이 충분하지 않은 경우, 충전 요청(to money-service)
        // 3. 송급타입 확인(고객/은행)
        // 3-1. 고객인 경우, from 고객 머니 감액, to 고객머니 증액(to banking-service)
        // 3-2. 은행인 경우, 법인계좌 -> 외부은행 계좌 펌뱅킹 요청(to banking-service)
        // 4. 송금 요청 상태를 성공으로 기록
        // 5. 송금 History 기록

        return null;
    }
}
