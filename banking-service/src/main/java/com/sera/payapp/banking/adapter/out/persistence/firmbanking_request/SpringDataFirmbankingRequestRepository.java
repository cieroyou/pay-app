package com.sera.payapp.banking.adapter.out.persistence.firmbanking_request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataFirmbankingRequestRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
}
