package com.sera.payapp.banking.adapter.out.persistence.firmbanking_request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "request_firmbanking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FirmbankingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long requestFirmbankingId;
    private String fromBankName;
    private String fromBankBankAccountNumber;
    private String toBankName;
    private String toBankBankAccountNumber;
    private int moneyAmount;
    private int firmbankingStatus;
    private UUID uuid;

    public FirmbankingRequestJpaEntity(String fromBankName, String fromBankBankAccountNumber, String toBankName, String toBankBankAccountNumber, int moneyAmount, int firmbankingStatus, UUID uuid) {
        this.fromBankName = fromBankName;
        this.fromBankBankAccountNumber = fromBankBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankBankAccountNumber = toBankBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingStatus = firmbankingStatus;
        this.uuid = uuid;
    }
}
