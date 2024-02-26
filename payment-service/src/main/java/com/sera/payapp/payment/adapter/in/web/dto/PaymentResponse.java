package com.sera.payapp.payment.adapter.in.web.dto;


import com.sera.payapp.payment.application.port.in.RequestPaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private String requestMembershipId;
    private int requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;
    private Instant approvedAt;

    public static PaymentResponse of(RequestPaymentInfo paymentInfo) {
        return new PaymentResponse(
                paymentInfo.getPaymentId(),
                paymentInfo.getRequestMembershipId(),
                paymentInfo.getRequestPrice(),
                paymentInfo.getFranchiseId(),
                paymentInfo.getFranchiseFeeRate(),
                paymentInfo.getPaymentStatus(),
                paymentInfo.getApprovedAt()
        );
    }
}
