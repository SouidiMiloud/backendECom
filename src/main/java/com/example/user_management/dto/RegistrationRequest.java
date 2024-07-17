package com.example.user_management.dto;


import lombok.Getter;

@Getter
public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String repeatPassword;
    private String role;
}