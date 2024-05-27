package com.decsaas.decmercado.gateway.adapter.in.controller.userdetails;

import com.decsaas.decmercado.gateway.adapter.out.repository.UserEntity;
import com.decsaas.decmercado.gateway.adapter.out.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byName = repository.findByName(username);
        return byName.map(User::new).orElseThrow(() -> new UsernameNotFoundException("Usuário inválido!"));
    }
}
