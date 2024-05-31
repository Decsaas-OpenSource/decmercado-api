package com.decsaas.decmercado.service.adapters.in.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private final String message;

}
