package com.sera.payapp.banking.adapter.out.persistence.registered_bank_account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataRegisteredBanckAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    Optional<RegisteredBankAccountJpaEntity> findByMembershipId(String membershipId);
}
