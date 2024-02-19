package com.sera.payapp.money.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 멤버 머니 충전 커맨드
 * 멤버 머니 충전 요청이 오면 commandGateway 를 통해 이 커맨드를 발행한다.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RechargingMoneyRequestCreatedCommand {
    /**
     * MemberMoney Aggregate Identifier
     * 멤버머니가 변경되는 것이므로 AggregateRoot 인 MemberMoney 의 Identifier 를 사용한다.
     */
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargingRequestId;
    private String membershipId;
    private int amount;

    @Override
    public String toString() {
        return "RechargingMoneyRequestCreatedCommand{" +
                "aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", rechargingRequestId='" + rechargingRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
