package com.sera.payapp.remittance.adapter.in.web;

import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.remittance.application.port.in.GetRemittanceHistoryUseCase;
import com.sera.payapp.remittance.application.port.in.dto.GetRemittanceQuery;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@WebAdapter
@RestController
public class GetRemittanceController {
    private final GetRemittanceHistoryUseCase getRemittanceUseCase;

    @GetMapping(path = "/remittance/{membershipId}")
    List<RequestRemittanceInfo> getRemittanceQuery(@PathVariable(name = "membershipId") String membershipId) {
        return getRemittanceUseCase.getRemittanceHistory(new GetRemittanceQuery(membershipId));
    }
}
