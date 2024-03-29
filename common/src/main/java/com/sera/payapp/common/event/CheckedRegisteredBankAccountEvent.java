package com.sera.payapp.common.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRegisteredBankAccountEvent {
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private boolean isChecked;
    private int amount;
    private String firmbankingRequestAggregateIdentifier;
    private String fromBankName;
    private String fromBankAccountNumber;

    @Override
    public String toString() {
        return "CheckedRegisteredBankAccountEvent{" +
                "rechargingRequestId='" + rechargingRequestId + '\'' +
                ", checkRegisteredBankAccountId='" + checkRegisteredBankAccountId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", isChecked=" + isChecked +
                ", amount=" + amount +
                ", firmbankingRequestAggregateIdentifier='" + firmbankingRequestAggregateIdentifier + '\'' +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                '}';
    }
}