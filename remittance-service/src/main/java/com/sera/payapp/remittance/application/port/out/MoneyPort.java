package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.application.port.out.dto.MoneyInfo;

public interface MoneyPort {
    MoneyInfo getMoneyInfo(String membershipId);

    boolean requestMoneyRecharging(String membershipId, int amount);

    boolean requestMoneyIncrease(String membershipId, int amount);

    boolean requestMoneyDecrease(String membershipId, int amount);
}
