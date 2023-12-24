package com.sera.payapp.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {

    Optional<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);
}
