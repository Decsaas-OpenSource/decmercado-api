package com.decsaas.decmercado.service.adapters.out.webhook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookResquest {

    private String userId;
    private String productId;
    private String productDescription;
}
