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
                .isValid(request.getIsValid())
                .build();

        // 이벤트방식이기 때문에 리턴값을 줄 수 없어서 null 로 바로 반환하도록 처리
        registerBankAccountUseCase.registerBankAccount(command);
        return ResponseEntity.ok(null);
    }

}
