package com.example.user_management.dto;


import lombok.Getter;

@Getter
public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
}