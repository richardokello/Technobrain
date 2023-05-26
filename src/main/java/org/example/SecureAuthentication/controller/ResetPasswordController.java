package org.example.SecureAuthentication.controller;

import org.example.SecureAuthentication.entity.DTO.ResetPasswordRequest;
import org.example.SecureAuthentication.entity.Users;
import org.example.SecureAuthentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ResetPasswordController {

    private final PasswordEncoder passwordEncoder;
    private  final UserRepository repository;

    public ResetPasswordController(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        String username = request.getUsername();
        String newPassword = request.getNewPassword();

        // Retrieve the user by username from your user service
        Users user = repository.findByUsername(username);

        // Update the user's password with the new password
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        // Save the updated user
       repository.save(user);
    }
}
