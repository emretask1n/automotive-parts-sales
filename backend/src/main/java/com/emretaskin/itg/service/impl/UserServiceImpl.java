package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.exception.ActivationException;
import com.emretaskin.itg.repository.UserRepository;
import com.emretaskin.itg.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUserExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    @Override
    public User findUserByUsername(String username){
        Objects.requireNonNull(username, "Username cannot be null");
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void activateUser(String activationCode) {
        User user = findUserByActivationCode(activationCode);
        user.setActivated(true);
        userRepository.save(user);
    }

    @Override
    public User findUserByActivationCode(String activationCode) {
        return userRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new ActivationException("Invalid activation code"));
    }

    @Override
    @Transactional
    public void resetFailedLoginAttempts(String username) {
        User user = findUserByUsername(username);
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void incrementFailedLoginAttempts(String username) {
        User user = findUserByUsername(username);
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

}
