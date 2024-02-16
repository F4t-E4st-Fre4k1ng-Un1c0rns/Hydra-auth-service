package com.yarvin.auth_service.controller;

import com.yarvin.auth_service.service.AuthenticationService;
import com.yarvin.auth_service.store.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public JwtAuthenticationResponseDto register(@RequestBody @Valid SignUpDto request) {
        return authenticationService.signUp(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public JwtAuthenticationResponseDto login(@RequestBody @Valid SignInDto request) {
        return authenticationService.signIn(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/authenticateToken")
    public AuthentificationTokenResponseDto authToken(@RequestBody @Valid TokenDto request) throws ErrorDto {
        return authenticationService.validateToken(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/authenticateId")
    public AuthentificationIdResponseDto authId(@RequestBody @Valid UuidDto request){
        return authenticationService.findById(request);
    }

}
