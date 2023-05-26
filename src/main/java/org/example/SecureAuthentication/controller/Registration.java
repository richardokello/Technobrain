package org.example.SecureAuthentication.controller;

import org.example.SecureAuthentication.entity.DTO.UserDto;
import org.example.SecureAuthentication.entity.Users;
import org.example.SecureAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Registration {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public ResponseEntity<String> registerUser(@RequestBody @Validated UserDto userDto) {
        // Check if the username already exists
         boolean userExist=userRepository.findUsersByUsername(userDto.getUsername());
        if (userExist) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        Users userdata=new Users();
        userdata.setUsername(userDto.getUsername());

        // Hash the user's password
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());


        // Set the hashed password
        userdata.setPassword(hashedPassword);


        // Save the user to the database

        userRepository.save(userdata);

        return ResponseEntity.ok("User registered successfully");
    }

}











