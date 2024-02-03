package com.sera.payapp.money.application.port.in;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;

public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {

    @NotBlank
    private final String targetMembershipId;

    public CreateMemberMoneyCommand(@NotBlank String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;
        this.validateSelf();
    }
}