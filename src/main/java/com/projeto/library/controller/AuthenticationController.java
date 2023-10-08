package com.projeto.library.controller;

import com.projeto.library.controller.dto.LoginRequest;
import com.projeto.library.controller.dto.TokenResponse;
import com.projeto.library.infra.security.TokenService;
import com.projeto.library.model.User;
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

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        var auth = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(), loginRequest.getPassword()
        );

        var authenticatedUser = authenticationManager.authenticate(auth);
        var token = tokenService.tokenGenerate((User) authenticatedUser.getPrincipal());

        return ResponseEntity.ok().body(new TokenResponse(token));
    }
}
