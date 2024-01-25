package com.sera.payapp.money.adapter.in.web.dto;

import com.sera.payapp.money.domain.ChangingMoneyType;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;
    private ChangingMoneyType moneyChangingType; // 0: 증액, 1: 감액
    private MoneyChangingResultStatus moneyChangingResultStatus;
    private int amount;

    public static MoneyChangingResultDetail fromMoneyChangingRequest(MoneyChangingRequest moneyChangingRequest) {
        return new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                moneyChangingRequest.getMoneyChangingType(),
                MoneyChangingResultStatus.SUCCEDED,
                moneyChangingRequest.getChangingMoneyAmount()
        );

    }

}

enum MoneyChangingResultStatus {
    SUCCEDED, // 성공
    FAILED, // 실패
    FAILED_BY_NOT_ENOUGH_MONEY, // 돈이 부족해서 실패
    FAILED_BY_NOT_EXIST_MEMBERSHIP, // 존재하지 않는 멤버십으로 실패
    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST // 존재하지 않는 돈 증액/감액 요청으로 실패

//    FAILED_BY_NOT_LINKED_BANK_ACCOUNT, // 연결된 계좌가 없어서 실패
//    FAILED_BY_NOT_VALID_BANK_ACCOUNT, // 유효하지 않은 계좌번호로 실패
}
