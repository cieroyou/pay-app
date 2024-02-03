package com.sera.payapp.money.adapter.axon.command;

import com.sera.payapp.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * CreateMemberMoneyCommandHandler 에서 EventQueue 에 넣는 Command.
 * 이벤트 이므로 수동태를 사용한다.
 */
@NoArgsConstructor
@Getter
public class MemberMoneyCreatedCommand extends SelfValidating<MemberMoneyCreatedCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String membershipId;

    public MemberMoneyCreatedCommand(String memberhipId) {
        this.membershipId = memberhipId;
        this.validateSelf();
    }

    @Override
    public String toString() {
        return "MemberMoneyCreatedCommand{" +
                "membershipId='" + membershipId + '\'' +
                '}';
    }
}
