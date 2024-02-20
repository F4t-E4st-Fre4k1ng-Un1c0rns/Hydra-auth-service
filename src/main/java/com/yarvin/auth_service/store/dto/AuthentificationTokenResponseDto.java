package com.yarvin.auth_service.store.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private Boolean isSponsor;
    private String inn;
    private String ogrn;
    private String sponsorAdress;


    private String name;
    private String secondName;
    private String fatherName;
    private String city;
    private Date dateOfBirth;
}
