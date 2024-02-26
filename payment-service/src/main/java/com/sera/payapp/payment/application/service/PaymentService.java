package com.sera.payapp.payment.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.payment.application.port.in.RequestPaymentCommand;
import com.sera.payapp.payment.application.port.in.RequestPaymentInfo;
import com.sera.payapp.payment.application.port.in.RequestPaymentUseCase;
import com.sera.payapp.payment.application.port.out.CreatePaymentPort;
import com.sera.payapp.payment.application.port.out.GetMembershipPort;
import com.sera.payapp.payment.application.port.out.GetRegisteredBankAccountPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PaymentService implements RequestPaymentUseCase {
    private final CreatePaymentPort createPaymentPort;
    private final GetMembershipPort getMembershipPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public RequestPaymentInfo requestPayment(RequestPaymentCommand command) {
        return null;
    }
}
