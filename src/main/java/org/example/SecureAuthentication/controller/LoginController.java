package org.example.SecureAuthentication.controller;

import org.example.SecureAuthentication.Config.JwtUtil;
import org.example.SecureAuthentication.entity.DTO.UserDto;
import org.example.SecureAuthentication.entity.Users;
import org.example.SecureAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
   @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody @Validated UserDto user) {
        Users existingUser = userRepository.findByUsername(user.getUsername());

        // Check if the user exists
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid username");
        }

        // Compare the hashed passwords
        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Generate a session token or JWT and return it to the user
            // Generate a JWT token
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);

        } else {
            return ResponseEntity.badRequest().body("Invalid password");
        }

}
}
