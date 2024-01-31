package com.sera.payapp.remittance.adapter.in.web.dto;

import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRemittanceRequest {
    @NotBlank
    private String fromMembershipId; // from membership
    @NotBlank
    private String toMembershipId; // to membership
    @NotBlank
    private String toBankName;
    @NotBlank
    private String toBankAccountNumber;

    private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌) //TODO enum으로 변경
    // 송금요청 금액
    private int amount;

    public RequestRemittanceCommand toCommand() {
        return RequestRemittanceCommand.builder()
                .fromMembershipId(fromMembershipId)
                .toMembershipId(toMembershipId)
                .toBankName(toBankName)
                .toBankAccountNumber(toBankAccountNumber)
                .remittanceType(remittanceType)
                .amount(amount)
                .build();
    }
}
