package com.sera.payapp.banking.adapter.axon.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestFirmbankingUpdatedCommand {
    @NotBlank
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private int firmbankingStatus;

    @Override
    public String toString() {
        return "RequestFirmbankingUpdatedCommand{" +
                "aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", firmbankingStatus=" + firmbankingStatus +
                '}';
    }
}
