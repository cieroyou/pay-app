package com.sera.payapp.money.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor()
public class MoneyChangingRequest {
    /**
     * 돈을 증액/감액 요청하는 도메인 Id
     */
    private final String moneyChangingRequestId;
    /**
     * 돈이 증액/감액 되는 고객의 Membership Id
     */
    private final String targetMembershipId;
    /**
     * 요청이 증액인지(0) / 감액인지(1)
     */
    private final ChangingMoneyType moneyChangingType;


    /**
     * 증액/감액 요청 금액
     */
    private final int changingMoneyAmount;
    /**
     * 증액 or 감액 요청에 대한 상태
     */
    private final boolean linkedStatusIsValid;

    private final ChangingMoneyStatus moneyChangingStatus;


    private final String uuid;
    private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            MoneyChangingType moneyChangingType,
            ChangingMoneyAmount changingMoneyAmount,
            LinkedStatusIsValid linkedStatusIsValid,
            MoneyChangingStatus changingMoneyStatus,
            Uuid uuid) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId.targetMembershipId,
                moneyChangingType.moneyChangingType,
                changingMoneyAmount.changingMoneyAmount,
                linkedStatusIsValid.linkedStatusIsValid,
                changingMoneyStatus.moneyChangingStatus,
                uuid.uuid,
                new Date());

    }

    //    @Value
//    public static class MembershipId {
//        String membershipId;
//
//        public MembershipId(String value) {
//            this.membershipId = value;
//        }
//    }
//
    @Value
    public static class MoneyChangingRequestId {
        String moneyChangingRequestId;

        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }

    }

    @Value
    public static class TargetMembershipId {
        String targetMembershipId;

        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }

    }

    @Value
    public static class MoneyChangingType {
        ChangingMoneyType moneyChangingType;

        public MoneyChangingType(ChangingMoneyType value) {
            this.moneyChangingType = value;
        }

        public MoneyChangingType(int value) {
            this.moneyChangingType = ChangingMoneyType.get(value);
        }

    }

    @Value
    public static class ChangingMoneyAmount {
        int changingMoneyAmount;

        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }

    }

    @Value
    public static class LinkedStatusIsValid {
        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }

        boolean linkedStatusIsValid;
    }

    @Value
    public static class MoneyChangingStatus {
        public MoneyChangingStatus(ChangingMoneyStatus value) {
            this.moneyChangingStatus = value;
        }

        public MoneyChangingStatus(int value) {
            this.moneyChangingStatus = ChangingMoneyStatus.get(value);
        }

        ChangingMoneyStatus moneyChangingStatus;
    }

    @Value
    public static class Uuid {
        public Uuid(UUID uuid) {
            this.uuid = uuid.toString();
        }

        String uuid;
    }
}


