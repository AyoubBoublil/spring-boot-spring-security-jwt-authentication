package com.boublil.jwtspringsecurity.controller;

import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.dto.UserForm;
import com.boublil.jwtspringsecurity.exception.ConfirmPasswordException;
import com.boublil.jwtspringsecurity.exception.UserAlreadyExist;
import com.boublil.jwtspringsecurity.mapper.UserMapper;
import com.boublil.jwtspringsecurity.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<UserDto> registerUser(@RequestBody UserForm userForm) {
        try {
            if (userForm != null)
                return ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(accountService.saveUser(userForm)));
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (UserAlreadyExist | ConfirmPasswordException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
