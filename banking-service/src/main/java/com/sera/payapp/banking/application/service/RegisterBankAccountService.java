package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.sera.payapp.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountCommand;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountUseCase;
import com.sera.payapp.banking.application.port.out.RegisterBankAccountPort;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerMemberAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        boolean isAccountValid = true;
        RegisteredBankAccountJpaEntity jpaEntity = registerMemberAccountPort.createBankAccount(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(isAccountValid)
        );
        return registeredBankAccountMapper.mapToDomainEntity(jpaEntity);
    }
}
