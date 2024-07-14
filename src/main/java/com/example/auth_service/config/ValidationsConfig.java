package com.example.auth_service.config;

import com.example.auth_service.services.models.validations.UserValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationsConfig {

    @Bean
    public UserValidations userValidation() {
        return new UserValidations();
    }
}
