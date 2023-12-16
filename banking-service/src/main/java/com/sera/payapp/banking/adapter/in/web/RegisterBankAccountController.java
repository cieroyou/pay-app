package com.sera.payapp.banking.adapter.in.web;


import com.sera.payapp.banking.adapter.in.web.dto.RegisterBankAccountRequest;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountCommand;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountUseCase;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(value = "/banking/account/register")
    ResponseEntity<RegisteredBankAccount> registerBankAccount(@RequestBody RegisterBankAccountRequest request) {

        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .build();

        return ResponseEntity.ok(registerBankAccountUseCase.registerBankAccount(command));
    }

}
