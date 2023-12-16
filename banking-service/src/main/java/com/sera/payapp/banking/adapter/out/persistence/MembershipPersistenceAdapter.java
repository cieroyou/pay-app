package com.sera.payapp.banking.adapter.out.persistence;

import com.sera.payapp.banking.application.port.out.RegisterBankAccountPort;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterBankAccountPort {
    private final SpringDataRegisteredBanckAccountRepository registeredBanckAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return registeredBanckAccountRepository.save(new RegisteredBankAccountJpaEntity(
                membershipId.getMembershipId(),
                bankName.getBankName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid())
        );

    }
}
