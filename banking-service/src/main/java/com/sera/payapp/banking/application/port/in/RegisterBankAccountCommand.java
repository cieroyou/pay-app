package com.sera.payapp.banking.application.port.in;

import com.sera.payapp.common.SelfValidating;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

    private String membershipId;
    // TODO: enum 으로 리팩토링 할 것
    private String bankName;
    private String bankAccountNumber;
    private Boolean isValid;

    public RegisterBankAccountCommand(String membershipId, String bankName, String bankAccountNumber, boolean isValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
        this.validateSelf();

    }

}
