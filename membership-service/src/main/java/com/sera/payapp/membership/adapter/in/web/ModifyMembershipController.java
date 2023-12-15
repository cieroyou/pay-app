package com.sera.payapp.membership.adapter.in.web;


import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.membership.adapter.in.web.dto.ModifyMembershipRequest;
import com.sera.payapp.membership.application.port.in.ModifyMembershipCommand;
import com.sera.payapp.membership.application.port.in.ModifyMembershipUseCase;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PutMapping(value = "/membership/modify/{membershipId}")
    ResponseEntity<Membership> modifyMembership(
            @PathVariable(name = "membershipId") String membershipId,
            @RequestBody ModifyMembershipRequest request) {

        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(membershipId)
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }

}
