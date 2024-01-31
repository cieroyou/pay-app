package com.sera.payapp.remittance.adapter.out.internal;

import com.sera.payapp.common.CommonHttpClient;
import com.sera.payapp.remittance.application.port.out.MoneyPort;
import com.sera.payapp.remittance.application.port.out.dto.MoneyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoneyServiceAdapter implements MoneyPort {
    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.money.url}")
    private String moneyServiceEndpoint;


    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
        // TODO 잔액 조회 api 생성
        return new MoneyInfo(membershipId, new Random().nextInt(1000000));
    }

    @Override
    public boolean requestMoneyRecharging(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyIncrease(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyDecrease(String membershipId, int amount) {
        return false;
    }
}
