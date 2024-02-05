package com.sera.payapp.money.adapter.out.persistence;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.money.application.port.out.CreateMemberMoneyPort;
import com.sera.payapp.money.application.port.out.GetMemberMoneyPort;
import com.sera.payapp.money.application.port.out.IncreaseMoneyPort;
import com.sera.payapp.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class MemberMoneyAdapter implements
        IncreaseMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        Optional<MemberMoneyJpaEntity> entity = memberMoneyRepository.findByMembershipId(Long.valueOf(membershipId.getMembershipId()));
        if (entity.isEmpty()) {
            return memberMoneyRepository.save(new MemberMoneyJpaEntity(Long.valueOf(membershipId.getMembershipId()), increaseMoneyAmount, null));
        }

        MemberMoneyJpaEntity memberMoneyJpaEntity = entity.get();
        memberMoneyJpaEntity.setBalance(memberMoneyJpaEntity.getBalance() + increaseMoneyAmount);
        return memberMoneyRepository.save(memberMoneyJpaEntity);
    }


    @Override
    public void createMemberMoney(MemberMoney.MembershipId membershipId,
                                  MemberMoney.MoneyAggregateIdentifier moneyAggregateIdentifier) {
        memberMoneyRepository.save(new MemberMoneyJpaEntity(
                Long.parseLong(membershipId.getMembershipId()),
                0, moneyAggregateIdentifier.getAggregateIdentifier()
        ));
    }

    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId membershipId) {
        return memberMoneyRepository.findByMembershipId(Long.parseLong(membershipId.getMembershipId()))
                .orElseGet(() -> {
                    MemberMoneyJpaEntity entity =
                            new MemberMoneyJpaEntity(Long.parseLong(membershipId.getMembershipId()), 0, null);
                    memberMoneyRepository.save(entity);
                    return entity;
                });
    }

}
