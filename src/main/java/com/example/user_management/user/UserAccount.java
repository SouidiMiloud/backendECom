package com.example.user_management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
public class UserAccount implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String address;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserAccountRole userRole;
    private String profileImagePath;
    private String cinPdfPath;
    private LocalDate dateOfBirth;
    private String nationality;
    private String bankInfo;


    public UserAccount(String firstName, String lastName, String username, String password, UserAccountRole role){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = role;
    }
    public UserAccount(String firstName, String lastName, String username,
                       String phone, String address, String password, UserAccountRole userRole,
                       String profileImagePath, String cinPdfPath, LocalDate dateOfBirth,
                       String nationality, String bankInfo) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.userRole = userRole;
        this.profileImagePath = profileImagePath;
        this.cinPdfPath = cinPdfPath;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.bankInfo = bankInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}