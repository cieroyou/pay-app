package com.sera.payapp.banking.adapter.in.web;

import com.sera.payapp.banking.application.port.in.GetRegisteredBankAccountQuery;
import com.sera.payapp.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.WebAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetBankAccountController {

    private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

    @GetMapping(value = "/banking/account/{registeredBankAccountId}")
    ResponseEntity<RegisteredBankAccount> getRegisteredBankAccount(@Schema(description = "RegisteredBankAccount ID", example = "1")
                                                                   @PathVariable(name = "registeredBankAccountId") String registeredBankAccountId) {
        GetRegisteredBankAccountQuery query = new GetRegisteredBankAccountQuery(registeredBankAccountId);
        return ResponseEntity.ok(getRegisteredBankAccountUseCase.getRegisteredBankAccount(query));
    }
}
