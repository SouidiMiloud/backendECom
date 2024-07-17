package com.example.user_management;

import com.example.user_management.user.UserAccount;
import com.example.user_management.user.UserAccountRepo;
import com.example.user_management.user.UserAccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Initialization implements CommandLineRunner {
    @Autowired
    private UserAccountRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.findByUsername("admin@gmail.com").isEmpty()) {
            UserAccount adminUser = new UserAccount();
            adminUser.setUsername("admin@gmail.com");
            adminUser.setAddress("admin address");
            adminUser.setBankInfo("admin bank info");
            adminUser.setCinPdfPath("");
            adminUser.setProfileImagePath("");
            adminUser.setDateOfBirth(LocalDate.of(2000, 07, 30));
            adminUser.setNationality("moroccan");
            adminUser.setPhone("admin phone");
            adminUser.setPassword(passwordEncoder.encode("adminpassword")); // Use a strong password here
            adminUser.setUserRole(UserAccountRole.ADMIN);
            userRepo.save(adminUser);
        }
    }
}
