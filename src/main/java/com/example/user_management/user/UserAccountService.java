package com.example.user_management.user;


import lombok.AllArgsConstructor;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserAccountService {

    private UserAccountRepo userRepo;

    public ResponseEntity<List<UserAccount>> getUsers(UserAccount userAccount) {
        if(userAccount.getUserRole().equals(UserAccountRole.NORMALUSER))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        List<UserAccount> users = userRepo.getUsers();
        return ResponseEntity.ok().body(users);
    }

    public ResponseEntity<Map<String, String>> updateUser(UserAccount userAccount) {
        Map<String, String> response = new HashMap<>();
        if(userAccount.getUserRole().equals(UserAccountRole.NORMALUSER)) {
            response.put("error", "not authorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        UserAccount user = userRepo.findByEmail(userAccount.getEmail()).get();
        user.setNationality(userAccount.getNationality());
        user.setPhone(userAccount.getPhone());
        user.setAddress(userAccount.getAddress());
        user.setDateOfBirth(userAccount.getDateOfBirth());
        user.setBankInfo(userAccount.getBankInfo());
        user.setCinPdfPath(userAccount.getCinPdfPath());
        user.setProfileImagePath(userAccount.getProfileImagePath());
        user.setUsername(userAccount.getUsername());

        userRepo.save(user);
        response.put("success", "successfully updated");
        return ResponseEntity.ok(response);
    }
}