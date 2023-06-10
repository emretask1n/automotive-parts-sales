package com.emretaskin.itg.service.checker.impl;

import com.emretaskin.itg.service.checker.interfaces.IsUserAlreadyExistByUsername;
import com.emretaskin.itg.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsUserAlreadyExistByUsernameImpl implements IsUserAlreadyExistByUsername {
    private final UserService userService;

    @Override
    public void check(String username) {
        if(userService.isUserExistsByUsername(username)){
            throw new UsernameNotFoundException("This username: " + username + " already in use.");
        }
    }
}
