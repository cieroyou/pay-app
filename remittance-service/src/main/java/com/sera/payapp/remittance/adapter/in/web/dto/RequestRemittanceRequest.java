package com.sera.payapp.remittance.adapter.in.web.dto;

import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRemittanceRequest {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private Boolean isValid;

    public RequestRemittanceCommand toCommand() {
        return RequestRemittanceCommand.builder()
                .membershipId(membershipId)
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .isValid(isValid)
                .build();
    }
}
