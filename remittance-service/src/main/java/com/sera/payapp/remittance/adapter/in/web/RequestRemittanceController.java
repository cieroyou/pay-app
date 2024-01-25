package com.sera.payapp.remittance.adapter.in.web;

import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.remittance.adapter.in.web.dto.RequestRemittanceRequest;
import com.sera.payapp.remittance.application.port.in.RequestRemittanceUseCase;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@WebAdapter
@RestController
public class RequestRemittanceController {
    private final RequestRemittanceUseCase requestRemittanceUseCase;

    @PostMapping(path = "/remittance/request")
    RequestRemittanceInfo requestRemittance(@RequestBody RequestRemittanceRequest request) {
        return requestRemittanceUseCase.requestRemittance(request.toCommand());
    }
}
