package com.sera.payapp.membership.adapter.out.persistence;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.membership.application.port.out.GetMembershipPort;
import com.sera.payapp.membership.application.port.out.ModifyMembershipPort;
import com.sera.payapp.membership.application.port.out.RegisterMembershipPort;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort,
        GetMembershipPort, ModifyMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;

    // 35분부터 다시 봐야함
    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsValid membershipIsValid,
                                                Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(new MembershipJpaEntity(membershipName.getNameValue(),
                membershipEmail.getEmailValue(),
                membershipAddress.getAddressValue(),
                membershipIsValid.isValidValue(),
                membershipIsCorp.isCorpValue()));
    }

    @Override
    public MembershipJpaEntity getMembership(Membership.MembershipId membershipId) {
        return membershipRepository.getById(
                Long.valueOf(membershipId.getMembershipId()));
    }

    public MembershipJpaEntity updateMembership(Membership.MembershipId membershipId,
                                                Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsValid membershipIsValid,
                                                Membership.MembershipIsCorp membershipIsCorp) {

        MembershipJpaEntity jpaEntity = getMembership(membershipId);
        jpaEntity.setName(membershipName.getNameValue());
        jpaEntity.setEmail(membershipEmail.getEmailValue());
        jpaEntity.setAddress(membershipAddress.getAddressValue());
        jpaEntity.setValid(membershipIsValid.isValidValue());
        jpaEntity.setCorp(membershipIsCorp.isCorpValue());
        membershipRepository.save(jpaEntity);
        return jpaEntity;
    }
}
