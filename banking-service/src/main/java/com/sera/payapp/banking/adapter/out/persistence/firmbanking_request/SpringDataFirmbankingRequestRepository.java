package com.sera.payapp.banking.adapter.out.persistence.firmbanking_request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataFirmbankingRequestRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
    Optional<FirmbankingRequestJpaEntity> findByAggregateIdentifier(String aggregateIdentifier);
}
