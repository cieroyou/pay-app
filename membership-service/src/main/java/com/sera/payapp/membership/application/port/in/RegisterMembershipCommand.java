package com.sera.payapp.membership.application.port.in;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String address;
    @AssertTrue
    private final boolean isValid;
    private final boolean isCorp;


    public RegisterMembershipCommand(String name, String email, String address, boolean isValid, boolean isCorp) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        this.validateSelf();
    }
}
