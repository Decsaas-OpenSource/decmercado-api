package com.decsaas.decmercado.gateway.adapter.in.controller;

import com.decsaas.decmercado.gateway.adapter.in.controller.jwt.JwtService;
import com.decsaas.decmercado.gateway.adapter.out.repository.UserEntity;
import com.decsaas.decmercado.gateway.adapter.out.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        return ResponseEntity.ok(jwtService.generatorToke(auth));
    }

    @PutMapping("/login/novo")
    public ResponseEntity<?> inserir(@RequestBody LoginRequest loginRequest) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(loginRequest.getLogin());
        userEntity.setEnabled(true);
        userEntity.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

        usersRepository.save(userEntity);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }
}
