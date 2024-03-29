package com.sera.payapp.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Required by Axon to construct an empty instance to initiate Event Sourcing.
public class MemberMoneyCreatedEvent {
    private String aggregateIdentifier;
    private String membershipId;


    @Override
    public String toString() {
        return "MemberMoneyCreatedEvent{" +
                "aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", membershipId='" + membershipId + '\'' +
                '}';
    }
}
