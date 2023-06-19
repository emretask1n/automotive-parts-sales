package com.emretaskin.itg.controller;

import com.emretaskin.itg.dto.request.EmailRequest;
import com.emretaskin.itg.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Activate user account")
    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam("code") String activationCode) {
        userService.activateUser(activationCode);
        return ResponseEntity.ok("Account activated successfully");
    }

    @Operation(summary = "Send mail to ADMIN")
    @PostMapping("/{userId}/send-email")
    public ResponseEntity<Void> sendEmailToAdmin(@PathVariable Long userId, @RequestBody EmailRequest emailRequest) {
        userService.sendEmailToAdmin(userId, emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.noContent().build();
    }
}
