package com.sera.payapp.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

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
    private final String firmbankingstatus; // TODO: enum 으로 리팩토링 할 것(0:요청, 1:완료, 2:실패)

    public static FirmbankingRequest generateFirmbankingRequest(
            FromBankName fromBankName,
            FirmbankingRequestId firmbankingRequestId,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            Firmbankingstatus firmbankingstatus) {
        return new FirmbankingRequest(
                fromBankName.fromBankName,
                firmbankingRequestId.firmbankingRequestId,
                fromBankAccountNumber.bankBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmbankingstatus.firmbankingstatus);
    }

    @Value
    public static class FromBankName {
        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public class FromBankAccountNumber {
        String bankBankAccountNumber;

        public FromBankAccountNumber(String value) {
            this.bankBankAccountNumber = value;
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
        String firmbankingstatus;

        public Firmbankingstatus(String value) {
            this.firmbankingstatus = value;
        }
    }


}
