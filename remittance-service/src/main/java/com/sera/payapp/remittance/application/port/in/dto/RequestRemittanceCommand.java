package com.sera.payapp.remittance.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RequestRemittanceCommand {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}
