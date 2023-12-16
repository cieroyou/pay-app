package com.sera.payapp.banking.adapter.in.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingRequest {
    private String fromBankName;
    private String bankBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;

}
// Compare this snippet from banking-service/src/main/java/com/sera/payapp/banking/adapter/in/web/dto/RequestFirmbankingResponse.java:
// package com.sera.payapp.banking.adapter.in.web.dto;
//
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// public class RequestFirmbankingResponse {
//     private String fromBankName;
//     private String bankBankAccountNumber;
//     private String bankName;
//
// }
// Compare this snippet from banking-service/src/main/java/com/sera/payapp/banking/adapter/out/persistence/RegisteredBankAccountJpaRepository.java:
// package com.sera.payapp.banking.adapter.out.persistence;
//
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
//
// @Repository
// public interface RegisteredBankAccountJpaRepository extends JpaRepository<RegisteredBankAccount, Long> {
// }
// Compare this snippet from banking-service/src/main/java/com/sera/payapp/banking/adapter/out/persistence/RegisteredBankAccountJpaEntity.java:
// package com.sera.payapp.banking.adapter.out.persistence;
//
// import lombok.*;
//
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
//
// @Entity
// @Getter
// @Setter
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class RegisteredBankAccountJpaEntity {
//
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String membershipId;
//     private String bankName;
//     private String bankAccountNumber;
//
// }
// Compare this snippet from banking-service/src/main/java/com/sera/payapp/banking/adapter/out/persistence/RegisteredBankAccountPersistenceAdapter.java:
// package com.sera.payapp.banking.adapter.out.persistence;
//
// import com.sera.payapp.banking.application.port.out.RegisteredBankAccountPersistencePort;
// import com.sera.payapp.banking.domain.RegisteredBankAccount;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Component;
//
// @Component
// @RequiredArgsConstructor
// public class RegisteredBankAccountPersistenceAdapter implements RegisteredBankAccountPersistencePort {
//
//     private final RegisteredBankAccountJpaRepository repository;
//