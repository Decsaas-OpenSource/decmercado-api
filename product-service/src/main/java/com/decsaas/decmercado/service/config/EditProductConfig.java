package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.EditProductAdapter;
import com.decsaas.decmercado.service.adapters.out.FindProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.EditProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditProductConfig {

    @Bean
    public EditProductUseCase editProductUseCase(
            EditProductAdapter editProductAdapter,
            FindProductAdapter findProductAdapter
    ) {
        return new EditProductUseCase(editProductAdapter, findProductAdapter);
    }
}
