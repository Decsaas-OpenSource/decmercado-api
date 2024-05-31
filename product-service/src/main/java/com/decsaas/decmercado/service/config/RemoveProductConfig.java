package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.FindProductAdapter;
import com.decsaas.decmercado.service.adapters.out.RemoveProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.RemoveProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveProductConfig {

    @Bean
    public RemoveProductUseCase removeProductCaseUse(
            RemoveProductAdapter removeProductAdapter,
            FindProductAdapter findProductAdapter
    ) {
        return new RemoveProductUseCase(removeProductAdapter, findProductAdapter);
    }
}
