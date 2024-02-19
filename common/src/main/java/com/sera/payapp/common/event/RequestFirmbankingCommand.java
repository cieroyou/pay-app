package com.sera.payapp.common.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


/**
 * to RequestFirmbankingFinishiedEvent
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestFirmbankingCommand {
    private String requestFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private String membershipId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won

    @Override
    public String toString() {
        return "RequestFirmbankingCommand{" +
                "requestFirmbankingId='" + requestFirmbankingId + '\'' +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", rechargeRequestId='" + rechargeRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
