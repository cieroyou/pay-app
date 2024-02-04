package com.sera.payapp.money.adapter.axon.command;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor // Required by Axon to construct an empty instance to initiate Event Sourcing.
public class MemberMoneyIncreasedCommand extends SelfValidating<MemberMoneyIncreasedCommand> {
    @NotBlank
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotBlank
    private String targetMembershipId;
    @NotNull
    private int amount;


    public MemberMoneyIncreasedCommand(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
