package com.sera.payapp.banking.adapter.out.persistence.registered_bank_account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registered_bank_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisteredBankAccountJpaEntity {

    @Id
    @GeneratedValue
    private Long registeredBankAccountId;
    private String membershipId;
    // TODO: enum 으로 리팩토링 할 것
    private String bankName;
    private String bankAccountNumber;
    private boolean linkedStatusIsValid;
    private String aggregateIdentifier;


    public RegisteredBankAccountJpaEntity(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid,
                                          String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.aggregateIdentifier = aggregateIdentifier;
    }


    @Override
    public String toString() {
        return "RegisteredBankAccountJpaEntity{" +
                "registeredBankAccountId=" + registeredBankAccountId +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                '}';
    }
}
