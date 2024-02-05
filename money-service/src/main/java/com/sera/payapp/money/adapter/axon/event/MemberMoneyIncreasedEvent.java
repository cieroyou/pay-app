package com.sera.payapp.money.adapter.axon.event;

import com.sera.payapp.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor // Required by Axon to construct an empty instance to initiate Event Sourcing.
public class MemberMoneyIncreasedEvent extends SelfValidating<MemberMoneyIncreasedEvent> {
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

