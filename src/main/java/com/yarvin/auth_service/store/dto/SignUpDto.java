package com.yarvin.auth_service.store.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpDto {
    private String username;
    private String email;
    private String password;

    private Boolean isSponsor;
    private String inn;
    private String ogrn;
    private String sponsorAdress;


    private String name;
    private String secondName;
    private String fatherName;
    private String city;
    private Date dateOfBirth;
    private String club;
}