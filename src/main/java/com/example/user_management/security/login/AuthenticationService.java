package com.example.user_management.security.login;


import com.example.user_management.dto.RegistrationRequest;
import com.example.user_management.security.JwtUtil;
import com.example.user_management.user.UserAccount;
import com.example.user_management.user.UserAccountRepo;
import com.example.user_management.user.UserAccountRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserAccountRepo userRepo;

    public ResponseEntity<Map<String, String>> register(RegistrationRequest request) throws IOException {

        Map<String, String> response = new HashMap<>();
        Optional<UserAccount> userOpt = userRepo.findByUsername(request.getUsername());
        if(userOpt.isPresent()) {
            response.put("error", request.getUsername() + " already exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        String password = request.getPassword();
        if(!password.equals(request.getRepeatPassword())){
            response.put("error", "passwords don't match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        password = passwordEncoder.encode(password);
        UserAccountRole role = UserAccountRole.NORMALUSER;
        if(request.getRole() != null)
            role = UserAccountRole.valueOf(request.getRole());
        UserAccount user = new UserAccount(request.getFirstname(), request.getLastname(), request.getUsername(), password, role);
        userRepo.save(user);
        response.put("success", "saved successfully");
        return ResponseEntity.ok().body(response);
    }
    public ResponseEntity<?> login(Credentials credentials){

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(), credentials.getPassword()
                    )
            );
            UserAccount user = (UserAccount) authentication.getPrincipal();
            return ResponseEntity.ok().body(new TokenResponse(user, jwtUtil.generateToken(user)));
        }
        catch(BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
