package com.decsaas.decmercado.service.adapters.out.webhook;

import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "my-list-service",
        configuration = HttpMessageConvertersAutoConfiguration.class)
public interface MyListProxy {

    @CircuitBreaker
    @PostMapping(value = "/v1/minha-lista/webhook")
    void webhook(@RequestBody WebHookResquest webHookResquest);

}
