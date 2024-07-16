package com.example.user_management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);

    @Query("SELECT u FROM UserAccount u WHERE u.userRole='NORMALUSER'")
    List<UserAccount> getUsers();
}