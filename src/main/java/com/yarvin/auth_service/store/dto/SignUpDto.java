package com.yarvin.auth_service.store.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;
    private String password;
}