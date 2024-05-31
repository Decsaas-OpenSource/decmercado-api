package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.FindProductAdapter;
import com.decsaas.decmercado.service.adapters.out.InsertProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.InsertProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertProductConfig {

    @Bean
    public InsertProductUseCase insertProductUseCase(
            InsertProductAdapter insertProductAdapter,
            FindProductAdapter findProductAdapter
    ) {
        return new InsertProductUseCase(insertProductAdapter, findProductAdapter);
    }
}
