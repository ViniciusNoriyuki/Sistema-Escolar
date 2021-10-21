package com.luxfacta.desafio.config;

import com.luxfacta.desafio.services.DBService;
import com.luxfacta.desafio.services.EmailService;
import com.luxfacta.desafio.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() {
        dbService.instantiateTestDataBase();

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
