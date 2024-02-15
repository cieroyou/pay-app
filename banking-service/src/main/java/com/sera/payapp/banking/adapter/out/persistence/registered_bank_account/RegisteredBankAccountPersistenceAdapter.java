package com.sera.payapp.banking.adapter.out.persistence.registered_bank_account;

import com.sera.payapp.banking.application.port.out.GetRegisteredBankAccountPort;
import com.sera.payapp.banking.application.port.out.RegisterBankAccountPort;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {
    private final SpringDataRegisteredBanckAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
            RegisteredBankAccount.AggregateIdentifier aggregateIdentifier) {
        return registeredBankAccountRepository.save(new RegisteredBankAccountJpaEntity(
                membershipId.getMembershipId(),
                bankName.getBankName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid(),
                aggregateIdentifier.getAggregateIdentifier()
        ));

    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId) {
        return registeredBankAccountRepository.findByMembershipId(membershipId.getMembershipId()).get();
    }
}
