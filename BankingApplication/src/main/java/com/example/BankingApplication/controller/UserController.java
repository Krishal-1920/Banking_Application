package com.example.BankingApplication.controller;


import com.example.BankingApplication.model.UserModel;
import com.example.BankingApplication.repository.UserRepository;
import com.example.BankingApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Updating New User
    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateUser(@RequestBody UserModel updatedUserModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return ResponseEntity.ok(userService.updateUser(updatedUserModel, authenticatedEmail));
    }

}
