package com.sera.payapp.banking.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFirmbankingCommand {
    private String firmbankingAggregateIdentifier;
    private int status;
}
