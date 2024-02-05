package com.sera.payapp.banking.adapter.axon.command;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Command 에 필수
@Getter
public class RequestFirmbankingCreatedCommand extends SelfValidating<RequestFirmbankingCreatedCommand> {
    @NotBlank
    private String fromBankName;
    @NotBlank
    private String fromBankAccountNumber;
    @NotBlank
    private String toBankName;
    @NotBlank
    private String toBankAccountNumber;
    private int amount;

    @Builder
    public RequestFirmbankingCreatedCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.amount = amount;
        this.validateSelf();
    }

    @Override
    public String toString() {
        return "RequestFirmbankingCreatedCommand{" +
                "fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
