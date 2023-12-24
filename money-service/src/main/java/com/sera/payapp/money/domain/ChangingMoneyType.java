package com.sera.payapp.money.domain;

import lombok.Getter;

public enum ChangingMoneyType {
    INCREASING(0), // 증액
    DECREASING(1) // 감액
    ;

    @Getter
    private final int value;

    ChangingMoneyType(int value) {
        this.value = value;
    }

    public static ChangingMoneyType get(int value) {
        return value == 0 ? INCREASING : DECREASING;
    }
}
