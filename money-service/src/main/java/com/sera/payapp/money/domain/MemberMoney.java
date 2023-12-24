package com.sera.payapp.money.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor()
public class MemberMoney {

    @Getter
    private final String memberMoneyId;
    @Getter
    private final String membershipId;
    /**
     * 잔액
     */
    @Getter
    private final int balance;


    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance) {
        return new MemberMoney(
                memberMoneyId.memberMoneyId,
                membershipId.membershipId,
                balance.balance
        );
    }

    @Value
    public static class MemberMoneyId {
        String memberMoneyId;

        public MemberMoneyId(String value) {
            this.memberMoneyId = value;
        }
    }

    @Value
    public static class MembershipId {
        String membershipId;

        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class Balance {
        int balance;

        public Balance(int value) {
            this.balance = value;
        }
    }
}
