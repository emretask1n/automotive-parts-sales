package com.emretaskin.itg.entity;

import com.emretaskin.itg.constants.Constants;
import com.emretaskin.itg.enums.UserRole;
import com.emretaskin.itg.validation.EmailFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameSurname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    @EmailFormat
    private String email;

    @Column(nullable = false)
    private int failedLoginAttempts = 0;

    @Column(nullable = false)
    private String activationCode;

    @Column(nullable = false)
    private boolean activated = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CreditCard> creditCards = new ArrayList<>();

    public User(String nameSurname, String username, String password, String email, String activationCode, UserRole role) {
        this.nameSurname = nameSurname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationCode = activationCode;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return failedLoginAttempts < Constants.MAX_FAILED_LOGIN_ATTEMPTS;
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
