package com.sera.payapp.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor // Command 에 필수
@Getter
public class RegisteredBankAccountCreatedEvent {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @Override
    public String toString() {
        return "RegisteredBankAccountCreatedCommand{" +
                "membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
