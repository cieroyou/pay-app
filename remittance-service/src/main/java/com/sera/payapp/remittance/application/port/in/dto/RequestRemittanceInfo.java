package com.sera.payapp.remittance.application.port.in.dto;

import com.sera.payapp.remittance.domain.RemittanceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestRemittanceInfo {
    private String remittanceRequestId;
    private String remittanceFromMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌) //TODO enum으로 변경
    // 송금요청 금액
    private String remittanceStatus; //TODO enum으로 변경
    private int amount;

    public static RequestRemittanceInfo fromEntity(RemittanceRequest remittanceRequest) {
        return RequestRemittanceInfo.builder()
                .remittanceRequestId(remittanceRequest.getRemittanceRequestId())
                .remittanceFromMembershipId(remittanceRequest.getRemittanceFromMembershipId())
                .toBankName(remittanceRequest.getToBankName())
                .toBankAccountNumber(remittanceRequest.getToBankAccountNumber())
                .remittanceType(remittanceRequest.getRemittanceType())
                .remittanceStatus(remittanceRequest.getRemittanceStatus())
                .amount(remittanceRequest.getAmount())
                .build();
    }
}
