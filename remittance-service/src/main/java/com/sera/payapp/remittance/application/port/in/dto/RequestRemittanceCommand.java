package com.sera.payapp.remittance.application.port.in.dto;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {
    @NotBlank
    private String fromMembershipId; // from membership

    @NotBlank
    private String toMembershipId; // to membership

    @NotBlank
    private String toBankName;

    @NotBlank
    private String toBankAccountNumber;

    @NotNull
    private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)

    @NotNull
    private int amount;

    @Builder
    public RequestRemittanceCommand(String fromMembershipId, String toMembershipId, String toBankName, String toBankAccountNumber, int remittanceType, int amount) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;

        this.validateSelf();
    }
}
