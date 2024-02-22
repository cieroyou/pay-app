package com.sera.payapp.payment.adapter.out.persistence;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.payment.application.port.out.CreatePaymentPort;
import com.sera.payapp.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class PaymentPersistenceAdapter implements CreatePaymentPort {
    private final SpringDataPaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    @Override
    public Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate) {
        PaymentJpaEntity jpaEntity = paymentRepository.save(
                new PaymentJpaEntity(
                        requestMembershipId,
                        Integer.parseInt(requestPrice),
                        franchiseId,
                        franchiseFeeRate,
                        0, // 0: 승인, 1: 실패, 2: 정산 완료.
                        null
                )
        );
        return mapper.mapToDomainEntity(jpaEntity);
    }
}
