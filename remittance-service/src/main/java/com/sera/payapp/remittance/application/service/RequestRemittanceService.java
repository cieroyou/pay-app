package com.sera.payapp.remittance.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.remittance.application.port.in.RequestRemittanceUseCase;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import com.sera.payapp.remittance.application.port.out.MembershipPort;
import com.sera.payapp.remittance.application.port.out.dto.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@UseCase
@Service
@RequiredArgsConstructor
public class RequestRemittanceService implements RequestRemittanceUseCase {
    private final MembershipPort membershipPort;

    @Override
    public RequestRemittanceInfo requestRemittance(RequestRemittanceCommand command) {
        // 0. 송금 요청 상태를 시작 상태로 기록
        // 1. 고객 정보가 정상인지 확인(to membership-service)
        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
        // 2. 잔액 확인(to money-service)
        // 2-1. 잔액이 충분하지 않은 경우, 충전 요청(to money-service)
        // 3. 송급타입 확인(고객/은행)
        // 3-1. 고객인 경우, from 고객 머니 감액, to 고객머니 증액(to banking-service)
        // 3-2. 은행인 경우, 법인계좌 -> 외부은행 계좌 펌뱅킹 요청(to banking-service)
        // 4. 송금 요청 상태를 성공으로 기록
        // 5. 송금 History 기록

        return null;
    }
}
