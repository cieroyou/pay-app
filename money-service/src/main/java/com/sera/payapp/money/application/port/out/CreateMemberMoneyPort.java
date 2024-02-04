package com.sera.payapp.money.application.port.out;


import com.sera.payapp.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {
    void createMemberMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.MoneyAggregateIdentifier moneyAggregateIdentifier
    );
}
