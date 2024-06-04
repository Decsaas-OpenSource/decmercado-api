package com.decsaas.decmercado.service.config;

import com.decsaas.decmercado.service.adapters.out.FindMyListAdapter;
import com.decsaas.decmercado.service.adapters.out.InsertMyListAdapter;
import com.decsaas.decmercado.service.application.core.usecase.InsertMyListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertMyListConfig {

    @Bean
    public InsertMyListUseCase insertMyListUseCase(
            InsertMyListAdapter insertMyListAdapter,
            FindMyListAdapter findMyListAdapter
    ) {
        return new InsertMyListUseCase(insertMyListAdapter, findMyListAdapter);
    }
}
