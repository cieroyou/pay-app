package com.sera.payapp.banking.application.port.out;

import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountJpaEntity;
import com.sera.payapp.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJpaEntity createBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
            RegisteredBankAccount.AggregateIdentifier aggregateIdentifier
    );
}
