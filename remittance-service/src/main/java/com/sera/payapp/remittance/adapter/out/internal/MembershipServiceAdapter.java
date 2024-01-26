package com.sera.payapp.remittance.adapter.out.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.CommonHttpClient;
import com.sera.payapp.remittance.application.port.out.MembershipPort;
import com.sera.payapp.remittance.application.port.out.dto.MembershipStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MembershipServiceAdapter implements MembershipPort {
    private final CommonHttpClient membershipServiceHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${service.membership.url}")
    private String membershipServiceEndpoint;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String requestUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
        try {
            // TODO 정상응답이 아닌 경우 처리
            String jsonResponse = membershipServiceHttpClient.sendGetRequest(requestUrl).body();
            MembershipInfo member = objectMapper.readValue(jsonResponse, MembershipInfo.class);
            return new MembershipStatus(member.getMembershipId(), member.getIsValid());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
