package com.sera.payapp.money.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountWithAggregateIdentifier {
    private String registeredBankAccountId;
    private String aggregateIdentifier;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @Override
    public String toString() {
        return "RegisteredBankAccountWithAggregateIdentifier{" +
                "registeredBankAccountId='" + registeredBankAccountId + '\'' +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
