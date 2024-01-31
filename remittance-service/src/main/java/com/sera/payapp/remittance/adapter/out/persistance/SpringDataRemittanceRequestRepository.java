package com.sera.payapp.remittance.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
    List<RemittanceRequestJpaEntity> findAllByFromMembershipId(String fromMembershipId);
}
