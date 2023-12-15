package com.sera.payapp.membership.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.membership.adapter.out.persistence.MembershipJpaEntity;
import com.sera.payapp.membership.adapter.out.persistence.MembershipMapper;
import com.sera.payapp.membership.application.port.in.ModifyMembershipCommand;
import com.sera.payapp.membership.application.port.in.ModifyMembershipUseCase;
import com.sera.payapp.membership.application.port.out.ModifyMembershipPort;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;


    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.updateMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }

}