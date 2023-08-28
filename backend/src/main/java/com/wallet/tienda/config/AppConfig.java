package com.wallet.tienda.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * Bean for map model to DTO or DTO to model
     * @return modelMapper
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
