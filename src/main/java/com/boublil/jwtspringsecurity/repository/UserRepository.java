package com.boublil.jwtspringsecurity.repository;

import com.boublil.jwtspringsecurity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
