package com.sera.payapp.money.domain;

import lombok.Getter;

public enum ChangingMoneyStatus {
    REQUESTED(0), // 요청됨
    SUCCESS(1), // 성공
    FAILED(2), // 실패
    CANCLED(3) // 취소됨
    ;

    @Getter
    private final int value;

    ChangingMoneyStatus(int value) {
        this.value = value;
    }

    public static ChangingMoneyStatus get(int value) {
        switch (value) {
            case 1:
                return SUCCESS;
            case 2:
                return FAILED;
            case 3:
                return CANCLED;
            default:
                return REQUESTED;
        }
    }
}