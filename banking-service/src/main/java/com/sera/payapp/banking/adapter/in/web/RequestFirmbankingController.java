package com.sera.payapp.banking.adapter.in.web;


import com.sera.payapp.banking.adapter.in.web.dto.RequestFirmbankingRequest;
import com.sera.payapp.banking.adapter.in.web.dto.UpdateFirmbankingRequest;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingCommand;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingUseCase;
import com.sera.payapp.banking.application.port.in.UpdateFirmbankingCommand;
import com.sera.payapp.banking.application.port.in.UpdateFirmbankingUseCase;
import com.sera.payapp.banking.domain.FirmbankingRequest;
import com.sera.payapp.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;
    private final UpdateFirmbankingUseCase updateFirmbankingUseCase;

    @PostMapping(value = "/banking/firmbanking/request")
    ResponseEntity<FirmbankingRequest> registerBankAccount(@RequestBody RequestFirmbankingRequest request) {
        return ResponseEntity.ok(requestFirmbankingUseCase.requestFirmbanking(
                RequestFirmbankingCommand.builder()
                        .fromBankName(request.getFromBankName())
                        .bankBankAccountNumber(request.getBankBankAccountNumber())
                        .toBankName(request.getToBankName())
                        .toBankAccountNumber(request.getToBankAccountNumber())
                        .moneyAmount(request.getMoneyAmount())
                        .build()
        ));
    }

    @PutMapping(value = "/banking/firmbanking/update")
    void updateFirmbanking(@RequestBody UpdateFirmbankingRequest request) {
        updateFirmbankingUseCase.updateFirmbanking(
                new UpdateFirmbankingCommand(request.getFirmbankingAggregateIdentifier(), request.getStatus()));
    }


}
