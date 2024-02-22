package com.sera.payapp.payment.application.port.out;

import com.sera.payapp.payment.domain.Payment;

public interface CreatePaymentPort {
    Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate);
}
