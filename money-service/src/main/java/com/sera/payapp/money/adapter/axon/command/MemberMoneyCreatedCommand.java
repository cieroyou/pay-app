package com.sera.payapp.money.adapter.axon.command;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * // Required by Axon to construct an empty instance to initiate Event Sourcing.
 * 디폴트생성자가 있어야 함.
 */
@Getter
@NoArgsConstructor // Required by Axon to construct an empty instance to initiate Event Sourcing.
public class MemberMoneyCreatedCommand
        extends SelfValidating<MemberMoneyCreatedCommand> {
    @NotNull
    private String membershipId;


    public MemberMoneyCreatedCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }

    @Override
    public String toString() {
        return "CreateMoneyCommand{" +
                "membershipId='" + membershipId + '\'' +
                '}';
    }
}
