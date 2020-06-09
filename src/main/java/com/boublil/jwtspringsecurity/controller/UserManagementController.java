package com.boublil.jwtspringsecurity.controller;

import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.dto.UserForm;
import com.boublil.jwtspringsecurity.mapper.UserMapper;
import com.boublil.jwtspringsecurity.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagementController {
    private final AccountService accountService;
    private final UserMapper userMapper;

    public UserManagementController(AccountService accountService, UserMapper userMapper) {
        this.accountService = accountService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userMapper.usersToUserDtos(accountService.getAllUsers());
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserForm userForm) {
        if (userForm != null) {
            return userMapper.userToUserDto(accountService.saveUser(userForm));
        } else {
            throw new RuntimeException("Invalid Form");
        }

    }
}
