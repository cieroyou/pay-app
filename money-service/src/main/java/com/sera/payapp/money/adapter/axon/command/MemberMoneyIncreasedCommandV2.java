package com.sera.payapp.money.adapter.axon.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberMoneyIncreasedCommandV2 {
    @NotBlank
//    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotBlank
    private String targetMembershipId;
    @NotNull
    private int amount;
}
