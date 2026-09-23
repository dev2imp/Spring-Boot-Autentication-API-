package org.sollingo.controllers;

import org.sollingo.dto.AuthResponse;
import org.sollingo.dto.LoginRequest;
import org.sollingo.dto.RegisterRequest;
import org.sollingo.entity.UserEntity;
import org.sollingo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/api/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.register(
                request.getEmail(),
                request.getPassword()
        );
    }

    @GetMapping("api/users")
    public List<UserEntity> getAllUsers(){
        return userService.getUsers();
    }


}
