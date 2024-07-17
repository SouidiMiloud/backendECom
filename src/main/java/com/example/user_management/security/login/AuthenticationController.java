package com.example.user_management.security.login;

import com.example.user_management.dto.RegistrationRequest;
import com.example.user_management.user.UserAccountRole;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authService;


    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequest request) throws IOException {

        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials){

        return authService.login(credentials);
    }
}