package com.sera.payapp.common.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 여러서비스에서 공통적으로 사용하는 사가 이벤트 커맨드
 * 계좌가 유효한지 체크하는 Command
 * 무조건 @TargetAggregateIdentifier 를 사용하여, 해당 Aggregate 를 찾아서 Command 를 전달해야 한다.
 * CheckRegisteredBankAccountCommand 룰 보낸 곳에서 CheckedRegisteredBankAccountEvent 를 비동기로 받게됨
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CheckRegisteredBankAccountCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier; // RegisteredBankAccount
    private String rechargeRequestId;
    private String membershipId;

    private String checkRegisteredBankAccountId;
    private String bankName;
    private String bankAccountNumber;
    private int amount;

    @Override
    public String toString() {
        return "CheckRegisteredBankAccountCommand{" +
                "aggregateIdentifier='" + aggregateIdentifier + '\'' +
                ", rechargeRequestId='" + rechargeRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", checkRegisteredBankAccountId='" + checkRegisteredBankAccountId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
