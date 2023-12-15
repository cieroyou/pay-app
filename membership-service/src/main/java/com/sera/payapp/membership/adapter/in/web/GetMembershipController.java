package com.sera.payapp.membership.adapter.in.web;


import com.sera.payapp.membership.application.port.in.GetMembershipQuery;
import com.sera.payapp.membership.application.port.in.GetMembershipUseCase;
import com.sera.payapp.membership.common.WebAdapter;
import com.sera.payapp.membership.domain.Membership;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetMembershipController {

    private final GetMembershipUseCase getMembershipUseCase;

    @GetMapping(value = "/membership/{membershipId}")
    ResponseEntity<Membership> getMembership(@Schema(description = "Membership ID", example = "1")
                                             @PathVariable(name = "membershipId") String membershipId) {
        GetMembershipQuery query = new GetMembershipQuery(membershipId);
        return ResponseEntity.ok(getMembershipUseCase.getMembership(query));
    }
}
