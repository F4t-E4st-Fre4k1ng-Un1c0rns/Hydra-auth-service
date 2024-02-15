package com.yarvin.auth_service.controller;

import com.yarvin.auth_service.service.AuthenticationService;
import com.yarvin.auth_service.store.dto.JwtAuthenticationResponseDto;
import com.yarvin.auth_service.store.dto.SignInDto;
import com.yarvin.auth_service.store.dto.SignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public JwtAuthenticationResponseDto register(@RequestBody @Valid SignUpDto request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponseDto login(@RequestBody @Valid SignInDto request) {
        return authenticationService.signIn(request);
    }


}
