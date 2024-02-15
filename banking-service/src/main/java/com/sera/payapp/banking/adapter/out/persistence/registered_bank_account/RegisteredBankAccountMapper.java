package com.sera.payapp.banking.adapter.out.persistence.registered_bank_account;

import com.sera.payapp.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity) {

        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(registeredBankAccountJpaEntity.getRegisteredBankAccountId().toString()
                ),
                new RegisteredBankAccount.MembershipId(registeredBankAccountJpaEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(registeredBankAccountJpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccountJpaEntity.isLinkedStatusIsValid()),
                new RegisteredBankAccount.AggregateIdentifier(registeredBankAccountJpaEntity.getAggregateIdentifier())
        );
    }
}
