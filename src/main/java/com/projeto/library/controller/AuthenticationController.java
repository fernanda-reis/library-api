package com.projeto.library.controller;

import com.projeto.library.controller.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        var auth = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(), loginRequest.getPassword()
        );

        authenticationManager.authenticate(auth);

        return ResponseEntity.ok().build();
    }
}
