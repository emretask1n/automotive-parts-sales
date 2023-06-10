package com.emretaskin.itg.controller;

import com.emretaskin.itg.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activation")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Activate user account")
    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam("code") String activationCode) {
        userService.activateUser(activationCode);
        return ResponseEntity.ok("Account activated successfully");
    }
}
