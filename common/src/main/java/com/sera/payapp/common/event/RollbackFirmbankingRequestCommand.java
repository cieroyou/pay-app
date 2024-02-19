package com.sera.payapp.common.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 펌뱅킹 요청 롤백
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RollbackFirmbankingRequestCommand {
    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int moneyAmount;

    @Override
    public String toString() {
        return "RollbackFirmbankingRequestCommand{" +
                "rollbackFirmbankingId='" + rollbackFirmbankingId + '\'' +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", rechargeRequestId='" + rechargeRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
