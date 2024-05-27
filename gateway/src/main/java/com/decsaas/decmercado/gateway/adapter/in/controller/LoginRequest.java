package com.decsaas.decmercado.gateway.adapter.in.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String login;
    private String password;
}
