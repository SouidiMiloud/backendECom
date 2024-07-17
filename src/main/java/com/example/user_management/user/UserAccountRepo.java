package com.example.user_management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);
    @Query("SELECT u FROM UserAccount u WHERE u.userRole='NORMALUSER'")
    List<UserAccount> getUsers();


    @Modifying
    @Transactional
    @Query("DELETE FROM UserAccount u WHERE u.username=?1")
    void deleteByUsername(String username);
}