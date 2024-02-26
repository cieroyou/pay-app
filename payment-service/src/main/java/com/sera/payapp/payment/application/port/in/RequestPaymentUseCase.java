package com.sera.payapp.payment.application.port.in;

public interface RequestPaymentUseCase {
    RequestPaymentInfo requestPayment(RequestPaymentCommand command);
}
