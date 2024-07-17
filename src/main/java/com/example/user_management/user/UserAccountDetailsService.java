package com.example.user_management.user;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAccountDetailsService implements UserDetailsService {

    private UserAccountRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserAccount> user = userRepo.findByUsername(username);
        return user.orElseThrow(()->new UsernameNotFoundException(username + " not found"));
    }
}