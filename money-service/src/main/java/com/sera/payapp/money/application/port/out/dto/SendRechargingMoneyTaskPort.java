package com.sera.payapp.money.application.port.out.dto;

import com.sera.payapp.common.RechargingMoneyTask;

/**
 * 카프카로 머니충전에 들어가는 Task 를 보내는 포트
 */
public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(RechargingMoneyTask task);
}
