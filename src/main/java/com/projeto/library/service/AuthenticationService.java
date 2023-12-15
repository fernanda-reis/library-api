package com.projeto.library.service;

import com.projeto.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        UserDetails userDetails = repository.findByEmail(email);
        if(userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            return userDetails;
        }
    }
}
