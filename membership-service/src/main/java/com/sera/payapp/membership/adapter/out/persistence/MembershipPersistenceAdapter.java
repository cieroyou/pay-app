package com.sera.payapp.membership.adapter.out.persistence;

import com.sera.payapp.membership.application.port.out.RegisterMembershipPort;
import com.sera.payapp.membership.common.PersistenceAdapter;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;

    // 35분부터 다시 봐야함
    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(new MembershipJpaEntity(membershipName.getNameValue(),
                membershipEmail.getEmailValue(),
                membershipAddress.getAddressValue(),
                membershipIsValid.isValidValue(),
                membershipIsCorp.isCorpValue()));
    }
}
