package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.UpdateBatchProductAdapter;
import com.decsaas.decmercado.service.application.core.usecase.UpdateBatchProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateBatchProductConfig {

    @Bean
    public UpdateBatchProductUseCase updateBatchProductUseCase(
            UpdateBatchProductAdapter updateBatchProductAdapter
    ) {
        return new UpdateBatchProductUseCase(updateBatchProductAdapter);
    }
}
