package com.sera.payapp.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberMoneyCreatedEvent {
    private String membershipId;

    @Override
    public String toString() {
        return "MemberMoneyCreatedEvent{" +
                "membershipId='" + membershipId + '\'' +
                '}';
    }
}
