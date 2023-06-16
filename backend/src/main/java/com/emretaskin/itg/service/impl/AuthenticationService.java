package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.dto.request.LoginRequest;
import com.emretaskin.itg.dto.request.RegisterRequest;
import com.emretaskin.itg.dto.response.LoginResponse;
import com.emretaskin.itg.dto.response.RegisterResponse;
import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.exception.ActivationException;
import com.emretaskin.itg.service.interfaces.UserLogService;
import com.emretaskin.itg.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final UserLogService userLogService;
    private final PasswordEncoder passwordEncoder;
    public RegisterResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();

        String activationCode = UUID.randomUUID().toString();

        User newUser = User.builder().nameSurname(registerRequest.getNameSurname())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(email)
                .activationCode(activationCode)
                .build();

        userService.saveUser(newUser);

        String activationLink = "http://localhost:8086/api/v1/activation/activate?code=" + activationCode;

        String emailSubject = "Account Activation";
        String emailBody = "Please click the following link to activate your account: " + activationLink;

        emailService.sendEmail(email, emailSubject, emailBody);

        return RegisterResponse.builder()
                .message("Successfully registered. Please activate your account from your email.")
                .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            userLogService.logFailedLoginAttempt(loginRequest.getUsername());
            throw ex;
        }

        userService.resetFailedLoginAttempts(loginRequest.getUsername());

        var user = userService.findUserByUsername(loginRequest.getUsername());

        if (!user.isActivated()) {
            throw new ActivationException("User is not activated");
        }

        if (user.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }

        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder().id(user.getId())
                .nameSurname(user.getNameSurname())
                .jwtToken(jwtToken)
                .build();
    }
}
