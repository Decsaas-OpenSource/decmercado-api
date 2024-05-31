package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.FindProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.FindProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductConfig {

    @Bean
    public FindProductUseCase findProductUseCase(
            FindProductAdapter findProductAdapter
    ) {
        return new FindProductUseCase(findProductAdapter);
    }
}
