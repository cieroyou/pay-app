package com.sera.payapp.banking.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.banking.adapter.in.web.dto.RegisterBankAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// springboot3 에서의 테스트: @SpringBootTest 내부에 @ExtendedWith 어노테이션을 가지고 있어서 @SpringBootTest 하나만으로 충분함
@SpringBootTest
@AutoConfigureMockMvc
class RegisterBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testRegisterBankAccount() throws Exception{
        RegisterBankAccountRequest request = new RegisterBankAccountRequest("1", "기업은행", "123-34-45");
//        RegisteredBankAccount expect = new RegisteredBankAccount()
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/banking/account/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}