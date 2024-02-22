package com.sera.payapp.payment.adapter.in.web;

import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.payment.adapter.in.web.dto.PaymentRequest;
import com.sera.payapp.payment.adapter.in.web.dto.PaymentResponse;
import com.sera.payapp.payment.application.port.in.RequestPaymentInfo;
import com.sera.payapp.payment.application.port.in.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {

    private final RequestPaymentUseCase requestPaymentUseCase;

    @PostMapping(path = "/payment/request")
    PaymentResponse requestPayment(PaymentRequest request) {
        RequestPaymentInfo paymentInfo = requestPaymentUseCase.requestPayment(request.toCommand());
        return PaymentResponse.of(paymentInfo);
    }
}
