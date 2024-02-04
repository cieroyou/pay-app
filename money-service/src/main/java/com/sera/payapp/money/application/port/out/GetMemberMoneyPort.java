package com.sera.payapp.money.application.port.out;

import com.sera.payapp.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.sera.payapp.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId membershipId);
}
