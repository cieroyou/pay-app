package com.sera.payapp.money.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue
    private Long memberMoneyId;

    private Long membershipId;
    private int balance;
    private String aggregateIdentifier;

    public MemberMoneyJpaEntity(Long membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public String toString() {
        return "MemberMoneyJpaEntity{" +
                "memberMoneyId=" + memberMoneyId +
                ", membershipId=" + membershipId +
                ", balance=" + balance +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                '}';
    }
}
