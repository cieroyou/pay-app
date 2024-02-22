package com.sera.payapp.payment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Payment {

    private Long paymentId;
    private String requestMembershipId;
    private int requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;
    private Date approvedAt;

    public static Payment generatePayment(
            PaymentId paymentId,
            RequestMembershipId requestMembershipId,
            RequestPrice requestPrice,
            FranchiseId franchiseId,
            FranchiseFeeRate franchiseFeeRate,
            PaymentStatus paymentStatus,
            ApprovedAt approvedAt
    ) {
        return new Payment(
                paymentId.paymentId,
                requestMembershipId.requestMembershipId,
                requestPrice.requestPrice,
                franchiseId.franchiseId,
                franchiseFeeRate.franchiseFeeRate,
                paymentStatus.paymentStatus,
                approvedAt.approvedAt
        );
    }

    @Value
    public static class PaymentId {
        public PaymentId(long value) {
            this.paymentId = value;
        }

        long paymentId;
    }

    @Value
    public static class RequestMembershipId {
        public RequestMembershipId(String value) {
            this.requestMembershipId = value;
        }

        String requestMembershipId;
    }

    @Value
    public static class RequestPrice {
        public RequestPrice(int value) {
            this.requestPrice = value;
        }

        int requestPrice;
    }

    @Value
    public static class FranchiseId {
        public FranchiseId(String value) {
            this.franchiseId = value;
        }

        String franchiseId;
    }

    @Value
    public static class FranchiseFeeRate {
        public FranchiseFeeRate(String value) {
            this.franchiseFeeRate = value;
        }

        String franchiseFeeRate;
    }

    @Value
    public static class PaymentStatus {
        public PaymentStatus(int value) {
            this.paymentStatus = value;
        }

        int paymentStatus;
    }

    @Value
    public static class ApprovedAt {
        public ApprovedAt(Date value) {
            this.approvedAt = value;
        }

        Date approvedAt;
    }

}