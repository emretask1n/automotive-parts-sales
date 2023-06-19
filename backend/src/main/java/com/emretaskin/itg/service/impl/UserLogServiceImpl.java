package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.entity.UserLog;
import com.emretaskin.itg.repository.UserLogRepository;
import com.emretaskin.itg.service.interfaces.UserLogService;
import com.emretaskin.itg.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserLogServiceImpl implements UserLogService {
    private final UserService userService;
    private final UserLogRepository userLogRepository;
    @Override
    @Transactional
    public void logFailedLoginAttempt(String username) {
        User user = userService.findUserByUsername(username);

        UserLog userLog = UserLog.builder()
                .user(user)
                .logDate(LocalDateTime.now())
                .logMessage("Failed login attempt")
                .build();

        userLogRepository.save(userLog);
    }
}
