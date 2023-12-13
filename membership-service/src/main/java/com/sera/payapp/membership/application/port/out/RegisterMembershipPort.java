package com.sera.payapp.membership.application.port.out;

import com.sera.payapp.membership.adapter.out.persistence.MembershipJpaEntity;
import com.sera.payapp.membership.domain.Membership;

public interface RegisterMembershipPort {

    MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                         Membership.MembershipEmail membershipEmail,
                                         Membership.MembershipAddress membershipAddress,
                                         Membership.MembershipIsValid membershipIsValid,
                                         Membership.MembershipIsCorp membershipIsCorp);
}
