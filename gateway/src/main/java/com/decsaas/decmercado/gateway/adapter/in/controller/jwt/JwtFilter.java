package com.decsaas.decmercado.gateway.adapter.in.controller.jwt;

import com.decsaas.decmercado.gateway.adapter.in.controller.userdetails.User;
import com.decsaas.decmercado.gateway.adapter.in.controller.userdetails.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

@Component
public class JwtFilter implements WebFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServicesImpl userDetailsServices;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Jwt jwt = jwtService.isTokenValid(token);

            if (jwt == null ||
                    Objects.requireNonNull(jwt.getExpiresAt()).isBefore(new Date().toInstant()))
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido!"));

            User user = userDetailsServices.loadUserByUsername(jwt.getClaim("sub"));

            try {
                AccountStatusUserDetailsChecker checker = new AccountStatusUserDetailsChecker();
                checker.check(user);
            } catch (Exception ex) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage()));
            }

            exchange.getRequest().mutate().header("userId", user.getId());
        }
        return chain.filter(exchange);
    }
}

