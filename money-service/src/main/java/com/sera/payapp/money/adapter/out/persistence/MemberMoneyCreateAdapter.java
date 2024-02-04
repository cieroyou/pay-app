package com.sera.payapp.money.adapter.out.persistence;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.money.application.port.out.CreateMemberMoneyPort;
import com.sera.payapp.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MemberMoneyCreateAdapter implements CreateMemberMoneyPort {
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public void createMemberMoney(MemberMoney.MembershipId membershipId,
                                  MemberMoney.MoneyAggregateIdentifier moneyAggregateIdentifier) {
        memberMoneyRepository.save(new MemberMoneyJpaEntity(
                Long.parseLong(membershipId.getMembershipId()),
                0, moneyAggregateIdentifier.getAggregateIdentifier()
        ));
    }
}
