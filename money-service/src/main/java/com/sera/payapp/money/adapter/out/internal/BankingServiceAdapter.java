package com.sera.payapp.money.adapter.out.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.CommonHttpClient;
import com.sera.payapp.money.application.port.out.GetRegisteredBankAccountPort;
import com.sera.payapp.money.application.port.out.dto.RegisteredBankAccountWithAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BankingServiceAdapter implements GetRegisteredBankAccountPort {
    private final CommonHttpClient commonHttpClient;
    @Value("${service.banking.url}")
    private String bankingServiceUrl;

    private final ObjectMapper mapper;

    @Override
    public RegisteredBankAccountWithAggregateIdentifier getRegisteredBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "banking/account", membershipId);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisteredBankAccountWithAggregateIdentifier(
                    registeredBankAccount.getRegisteredBankAccountId()
                    , registeredBankAccount.getAggregateIdentifier()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankAccountNumber()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
