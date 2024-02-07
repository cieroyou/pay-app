package com.sera.payapp.banking.adapter.axon.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Event에 필수
@Getter
public class RequestFirmbankingCreatedEvent {
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int amount;

    @Builder
    public RequestFirmbankingCreatedEvent(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RequestFirmbankingCreatedEvent{" +
                "fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
