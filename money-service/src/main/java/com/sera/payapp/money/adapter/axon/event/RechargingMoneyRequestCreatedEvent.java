package com.sera.payapp.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 충전된 돈이 생성되었을 때 발생하는 이벤트(충전 동작 요청이 생성되었다는 이벤트)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RechargingMoneyRequestCreatedEvent {
    private String rechargingRequestId;
    private String membershipId;
    private int amount;
    private String registeredBankAccountAggregateIdentifier;
    private String bankName;
    private String bankAccountNumber;

    @Override
    public String toString() {
        return "RechargingMoneyRequestCreatedEvent{" +
                "rechargingRequestId='" + rechargingRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", amount=" + amount +
                ", registeredBankAccountAggregateIdentifier='" + registeredBankAccountAggregateIdentifier + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
