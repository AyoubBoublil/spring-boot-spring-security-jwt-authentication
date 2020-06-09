package com.boublil.jwtspringsecurity.service.impl;

import com.boublil.jwtspringsecurity.dto.UserForm;
import com.boublil.jwtspringsecurity.model.AppRole;
import com.boublil.jwtspringsecurity.model.AppUser;
import com.boublil.jwtspringsecurity.repository.RoleRepository;
import com.boublil.jwtspringsecurity.repository.UserRepository;
import com.boublil.jwtspringsecurity.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser saveUser(UserForm userForm) {
        AppUser user = userRepository.findByUsername(userForm.getUsername());
        if (user != null) throw new RuntimeException("This user already exist, try with an other username");
        if (!userForm.getPassword().equals(userForm.getConfirmedPassword()))
            throw new RuntimeException("You must confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(userForm.getUsername());
        appUser.setActived(true);
        appUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        appUser = userRepository.save(appUser);
        addRoleToUser(appUser.getUsername(), "USER");
        return appUser;
    }


    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppRole role = roleRepository.findByRole(roleName);
        AppUser user = userRepository.findByUsername(userName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
