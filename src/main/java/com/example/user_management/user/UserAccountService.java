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

    public void updateUser(UserAccount authUser, UserAccount userAccount) {
        if(authUser.getUserRole().equals(UserAccountRole.NORMALUSER)) {
            return;
        }
        UserAccount user = userRepo.findByUsername(userAccount.getUsername()).get();
        user.setNationality(userAccount.getNationality());
        user.setPhone(userAccount.getPhone());
        user.setAddress(userAccount.getAddress());
        user.setDateOfBirth(userAccount.getDateOfBirth());
        user.setBankInfo(userAccount.getBankInfo());
        user.setCinPdfPath(userAccount.getCinPdfPath());
        user.setProfileImagePath(userAccount.getProfileImagePath());

        userRepo.save(user);
    }
    public void deleteUserByUsername(String username) {
        userRepo.deleteByUsername(username);
    }
}