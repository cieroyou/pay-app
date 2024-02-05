package com.sera.payapp.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberMoneyIncreasedEventV22 {
    private String aggregateIdentifier;
    private String targetMembershipId;
    private int amount;

    @Override
    public String toString() {
        return "MemberMoneyIncreasedEvent{" +
                "aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", targetMembershipId='" + targetMembershipId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
