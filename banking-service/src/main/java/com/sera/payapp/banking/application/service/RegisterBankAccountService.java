package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.out.external.bank.BankAccountInfo;
import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountJpaEntity;
import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountMapper;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountCommand;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountUseCase;
import com.sera.payapp.banking.application.port.out.GetMembershipPort;
import com.sera.payapp.banking.application.port.out.RegisterBankAccountPort;
import com.sera.payapp.banking.application.port.out.RequestBankAccountInfoPort;
import com.sera.payapp.banking.application.port.out.dto.MembershipStatus;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@UseCase
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerMemberAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipPort getMembershipPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 1. 멤버쉽 상태 확인(멤버쉽 서비스에 GET 멤버쉽 정보 API 요청)
        MembershipStatus membershipStatus = getMembershipPort.getMembershipStatus(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            // TODO: 에러 정의 필요, null 대신 에러 Response 보내도록 처리
            return null;
        }

        // 1. 외부 은행에 계좌등록 요청온 계좌가 정상인지 확인
        BankAccountInfo bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(command.getBankName(), command.getBankAccountNumber());
        boolean isAccountValid = bankAccountInfo.isValid();

        // 2. 정상이면 등록할 계좌를 등록
        if (!isAccountValid) {
            return null;
        }

        RegisteredBankAccountJpaEntity jpaEntity = registerMemberAccountPort.createBankAccount(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(isAccountValid),
                new RegisteredBankAccount.AggregateIdentifier(UUID.randomUUID().toString()
                ));
        return registeredBankAccountMapper.mapToDomainEntity(jpaEntity);
    }
}
