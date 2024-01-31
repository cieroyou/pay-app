package com.sera.payapp.remittance.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestMapper;
import com.sera.payapp.remittance.application.port.in.RequestRemittanceUseCase;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import com.sera.payapp.remittance.application.port.out.BankingPort;
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
    private final BankingPort bankingPort;
    private final RequestRemittancePort requestRemittancePort;
    private final RemittanceRequestMapper mapper;


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
        // 3. 송급타입 확인(고객/은행)
        // TODO: refactor RemittanceType enum 으로 변경(고객/은행)
        if (command.getRemittanceType() == 0) {
            // 3-1. 고객인 경우, from 고객 머니 감액, to 고객머니 증액(to banking-service)
            boolean moneyDecreaseResult = moneyPort.requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
            if (!moneyDecreaseResult) return null;
            boolean moneyIncreaseResult = moneyPort.requestMoneyIncrease(command.getToMembershipId(), command.getAmount());
            if (!moneyIncreaseResult) return null;
        } else if (command.getRemittanceType() == 1) {
            // 3-2. 은행인 경우, 법인계좌 -> 외부은행 계좌 펌뱅킹 요청(to banking-service)
            boolean firmbankingResult = bankingPort.requestFirmbanking(command.getToBankName(), command.getToBankAccountNumber());
            if (!firmbankingResult) return null;
        }
        // 4. 송금 요청 상태를 성공으로 기록
        // TODO: 서비스단에서 직접 엔티티를 수정하는 것은 좋지 않음. 나중에 수정이 필요
        // TODO: "success" 대신에 enum 으로 변경
        entity.setRemittanceStatus("success");
        boolean saveResult = requestRemittancePort.saveRemittanceRequestHistory(entity);
        if (saveResult) return RequestRemittanceInfo.fromEntity(mapper.mapToDomainEntity(entity));
        else return null;
    }
}
