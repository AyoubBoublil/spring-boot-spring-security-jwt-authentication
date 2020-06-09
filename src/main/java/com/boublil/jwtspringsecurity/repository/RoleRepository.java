package com.boublil.jwtspringsecurity.repository;

import com.boublil.jwtspringsecurity.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRole(String roleName);
}
