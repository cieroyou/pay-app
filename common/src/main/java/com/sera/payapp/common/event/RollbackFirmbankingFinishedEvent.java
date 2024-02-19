package com.sera.payapp.common.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingFinishedEvent {
    private String rollbackFirmbankingId;
    private String membershipId;
    private String rollbackFirmbankingAggregateIdentifier;

    @Override
    public String toString() {
        return "RollbackFirmbankingFinishedEvent{" +
                "rollbackFirmbankingId='" + rollbackFirmbankingId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", rollbackFirmbankingAggregateIdentifier='" + rollbackFirmbankingAggregateIdentifier + '\'' +
                '}';
    }
}
