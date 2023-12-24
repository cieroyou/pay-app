package com.sera.payapp.money.application.port.in;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyCommand extends SelfValidating<IncreaseMoneyCommand> {
    @NotBlank
    private String targetMembershipId;
    @NotNull
    private int amount;
}
