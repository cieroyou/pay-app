package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountJpaEntity;
import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountMapper;
import com.sera.payapp.banking.application.port.in.GetRegisteredBankAccountQuery;
import com.sera.payapp.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.sera.payapp.banking.application.port.out.GetRegisteredBankAccountPort;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
public class GetRegisteredBankAccountService implements GetRegisteredBankAccountUseCase {
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountQuery query) {
        RegisteredBankAccountJpaEntity registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount(
                new RegisteredBankAccount.MembershipId(query.getMembershipId()));
        return registeredBankAccountMapper.mapToDomainEntity(registeredBankAccount);
    }
}
