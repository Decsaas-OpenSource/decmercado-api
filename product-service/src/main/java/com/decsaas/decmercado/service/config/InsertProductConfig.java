package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.repository.InsertProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.InsertProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertProductConfig {

    @Bean
    public InsertProductUseCase insertProductUseCase(
            InsertProductAdapter insertProductAdapter
    ) {
        return new InsertProductUseCase(insertProductAdapter);
    }
}
