package com.yarvin.auth_service.store.dto;

import lombok.Data;

@Data
public class SignInResponseDto {
    private String email;
    private String password;
}
