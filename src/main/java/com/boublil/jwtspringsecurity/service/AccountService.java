package com.boublil.jwtspringsecurity.service;

import com.boublil.jwtspringsecurity.dto.UserForm;
import com.boublil.jwtspringsecurity.exception.ConfirmPasswordException;
import com.boublil.jwtspringsecurity.exception.UserAlreadyExist;
import com.boublil.jwtspringsecurity.model.AppRole;
import com.boublil.jwtspringsecurity.model.AppUser;

import java.util.List;

public interface AccountService {

    List<AppUser> getAllUsers();

    AppUser saveUser(UserForm userForm) throws UserAlreadyExist, ConfirmPasswordException;

    AppRole saveRole(AppRole role);

    void addRoleToUser(String userName, String roleName);

    AppUser findUserByUserName(String userName);
}
