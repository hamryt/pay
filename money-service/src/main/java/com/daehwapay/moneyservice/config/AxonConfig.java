package com.daehwapay.moneyservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
//    @Bean
//    public JacksonSerializer eventSerializer() {
//        return JacksonSerializer.builder()
//                .objectMapper(new ObjectMapper())
//                .build();
//    }
}
