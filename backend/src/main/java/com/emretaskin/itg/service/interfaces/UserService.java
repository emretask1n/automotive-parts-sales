package com.emretaskin.itg.service.interfaces;

import com.emretaskin.itg.entity.User;
import org.aspectj.apache.bcel.classfile.Module;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    boolean isUserExistsByUsername(String username);

    User findUserByUsername(String username);

    void activateUser(String activationCode);

    User findUserByActivationCode(String activationCode);

    void resetFailedLoginAttempts(String username);

    void incrementFailedLoginAttempts(String username);

    User getUserById(Long id);
}
