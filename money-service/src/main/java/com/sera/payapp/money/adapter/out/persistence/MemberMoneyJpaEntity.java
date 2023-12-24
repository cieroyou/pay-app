package com.sera.payapp.money.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_money")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberMoneyJpaEntity {
    private Long memberMoneyId;
    private String membershipId;
    private int balance;

    public MemberMoneyJpaEntity(String membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }
}
