package com.sera.payapp.common.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * from RequestFirmbankingCommand
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingFinishedEvent {
    private String requestFirmbankingId;
    private String rechargingRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won
    private int status;
    private String requestFirmbankingAggregateIdentifier;

    @Override
    public String toString() {
        return "RequestFirmbankingFinishedEvent{" +
                "requestFirmbankingId='" + requestFirmbankingId + '\'' +
                ", rechargingRequestId='" + rechargingRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", status=" + status +
                ", requestFirmbankingAggregateIdentifier='" + requestFirmbankingAggregateIdentifier + '\'' +
                '}';
    }
}
