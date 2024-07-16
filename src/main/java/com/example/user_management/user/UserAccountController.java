package com.example.user_management.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserAccountController {

    private UserAccountService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getUsers(@AuthenticationPrincipal UserAccount userAccount){
        return userService.getUsers(userAccount);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@AuthenticationPrincipal UserAccount userAccount){
        return userService.updateUser(userAccount);
    }
}