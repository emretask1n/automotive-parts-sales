package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByActivationCode(String activationCode);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
}
