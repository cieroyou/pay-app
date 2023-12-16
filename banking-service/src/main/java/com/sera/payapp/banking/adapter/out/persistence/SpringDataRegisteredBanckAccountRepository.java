package com.sera.payapp.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRegisteredBanckAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
}
