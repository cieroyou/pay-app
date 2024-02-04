package com.sera.payapp.money.adapter.out.persistence;


import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.money.application.port.out.IncreaseMoneyPort;
import com.sera.payapp.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberMoneyAdapter implements IncreaseMoneyPort {
    private final SpringDataMemberMoneyRepository memberMoneyRepository;


    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        Optional<MemberMoneyJpaEntity> entity = memberMoneyRepository.findByMembershipId(Long.valueOf(membershipId.getMembershipId()));
        if (entity.isEmpty()) {
            return memberMoneyRepository.save(
                    new MemberMoneyJpaEntity(Long.valueOf(membershipId.getMembershipId()), increaseMoneyAmount, null));
        }

        MemberMoneyJpaEntity memberMoneyJpaEntity = entity.get();
        memberMoneyJpaEntity.setBalance(memberMoneyJpaEntity.getBalance() + increaseMoneyAmount);
        return memberMoneyRepository.save(memberMoneyJpaEntity);
    }
}
