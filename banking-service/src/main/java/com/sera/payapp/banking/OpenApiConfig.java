package com.sera.payapp.banking;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// components.securitySchemes.BearerAuth.type: http
//components.securitySchemes.BearerAuth.scheme: bearer
//@io.swagger.v3.oas.annotations.security.SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
public class OpenApiConfig {


    @Bean
    public OpenAPI getOpenApi() {
        Info info = new Info();

        return new OpenAPI()
                .info(info);
    }

}
