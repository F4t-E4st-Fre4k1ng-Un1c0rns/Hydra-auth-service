package com.yarvin.auth_service.store.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String username;
    private String password;
}
