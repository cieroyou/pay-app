package com.sera.payapp.money.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyChangingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long moneyChangingRequestId;
    private String targetMembershipId;
    private int moneyChangingType; // 0: 증액, 1: 감액
    private int bankAccountNumber;
    private boolean linkedStatusIsValid;
    private int moneyChangingStatus; // 0: 요청됨, 1: 성공, 2: 실패, 3: 취소됨
    private UUID uuid;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingTypeValue, int bankAccountNumber, boolean linkedStatusIsValid, int moneyChangingStatusValue, UUID uuid, Date createdAt) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingTypeValue;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.moneyChangingStatus = moneyChangingStatusValue;
        this.uuid = uuid;
        this.createdAt = new Timestamp(createdAt.getTime());
    }
}


