package com.sera.payapp.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 외부 은행에 펌뱅킹 요청 결과
 * 0: 성공, 1: 실패
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FirmbankingResult {
    private int resultCode; // 0: 성공, 1: 실해
}
