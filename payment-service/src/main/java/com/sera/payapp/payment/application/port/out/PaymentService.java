package com.sera.payapp.payment.application.port.out;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.payment.application.port.in.RequestPaymentInfo;
import com.sera.payapp.payment.application.port.in.RequestPaymentCommand;
import com.sera.payapp.payment.application.port.in.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PaymentService implements RequestPaymentUseCase {
    @Override
    public RequestPaymentInfo requestPayment(RequestPaymentCommand command) {
        return null;
    }
}
