package com.sera.payapp.banking.adapter.out.persistence.registered_bank_account;

import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRegisteredBanckAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
}
