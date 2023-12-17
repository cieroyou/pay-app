package com.sera.payapp.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {

    @Getter
    private final String fromBankName;
    @Getter
    private final String firmbankingRequestId;
    @Getter
    private final String fromBankAccountNumber;
    @Getter
    private final String toBankName;
    @Getter
    private final String toBankAccountNumber;
    @Getter
    private final int moneyAmount;
    @Getter
    private final int firmbankingStatus; // TODO: enum 으로 리팩토링 할 것(0:요청, 1:완료, 2:실패)
    @Getter
    private final UUID uuid;

    public static FirmbankingRequest generateFirmbankingRequest(
            FirmbankingRequestId firmbankingRequestId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            Firmbankingstatus firmbankingStatus,
            UUID uuid) {
        return new FirmbankingRequest(
                firmbankingRequestId.firmbankingRequestId,
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmbankingStatus.firmbankingStatus,
                uuid);
    }

    @Value
    public static class FromBankName {
        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {
        String fromBankAccountNumber;

        public FromBankAccountNumber(String value) {
            this.fromBankAccountNumber = value;
        }
    }

    @Value
    public static class FirmbankingRequestId {
        String firmbankingRequestId;

        public FirmbankingRequestId(String value) {
            this.firmbankingRequestId = value;
        }
    }

    @Value
    public static class ToBankName {
        String toBankName;

        public ToBankName(String value) {
            this.toBankName = value;
        }
    }

    @Value
    public static class ToBankAccountNumber {
        String toBankAccountNumber;

        public ToBankAccountNumber(String value) {
            this.toBankAccountNumber = value;
        }
    }

    @Value
    public static class MoneyAmount {
        int moneyAmount;

        public MoneyAmount(int value) {
            this.moneyAmount = value;
        }
    }

    @Value
    public static class Firmbankingstatus {
        int firmbankingStatus;

        public Firmbankingstatus(int value) {
            this.firmbankingStatus = value;
        }
    }

    @Override
    public String toString() {
        return "FirmbankingRequest{" +
                "fromBankName='" + fromBankName + '\'' +
                ", firmbankingRequestId='" + firmbankingRequestId + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", firmbankingStatus='" + firmbankingStatus + '\'' +
                '}';
    }
}
