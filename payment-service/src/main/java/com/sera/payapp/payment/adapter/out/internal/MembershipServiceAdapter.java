package com.sera.payapp.payment.adapter.out.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.sera.payapp.common.CommonHttpClient;
import com.sera.payapp.payment.application.port.out.GetMembershipPort;
import com.sera.payapp.payment.application.port.out.dto.MembershipInfo;
import com.sera.payapp.payment.application.port.out.dto.MembershipStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * membership service (내부 서비스) 로의 요청 구현부 Using httpclient
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MembershipServiceAdapter implements GetMembershipPort {
    private final CommonHttpClient commonHttpClient;
    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try {
            String response = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            // TODO: ObjectMapper 에러 처리, 에러 Response 규격처리 필요
            try {
                MembershipInfo membershipInfo = mapper.readValue(response, MembershipInfo.class);
                return new MembershipStatus(membershipId, membershipInfo.isValid());
            } catch (UnrecognizedPropertyException unrecognizedPropertyException) {
                return new MembershipStatus(membershipId, false);
            }
        } catch (Exception e) {
            log.error("Request Membership Service is failed cause {}", e.getMessage());
            return new MembershipStatus(membershipId, false);
        }

    }

}
