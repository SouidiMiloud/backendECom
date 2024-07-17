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

    @PostMapping("/edit")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal UserAccount user, @RequestBody UserAccount userAccount){
        userService.updateUser(user, userAccount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        userService.deleteUserByUsername(email);
        return ResponseEntity.noContent().build();
    }
}