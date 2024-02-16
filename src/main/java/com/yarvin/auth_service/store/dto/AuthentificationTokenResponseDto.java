package com.yarvin.auth_service.store.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthentificationTokenResponseDto {
    private String username;
    private String email;
    private String role;
    private UUID id;
}
