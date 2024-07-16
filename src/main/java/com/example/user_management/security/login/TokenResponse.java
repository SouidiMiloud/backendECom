package com.example.user_management.security.login;

import com.example.user_management.user.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TokenResponse {

    private UserAccount user;
    private String token;
}