package com.sera.payapp.payment.adapter.in.web.dto;

import com.sera.payapp.payment.application.port.in.RequestPaymentCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentRequest {
    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;

    public RequestPaymentCommand toCommand() {
        return new RequestPaymentCommand(
                requestMembershipId,
                requestPrice,
                franchiseId,
                franchiseFeeRate
        );
    }
}
