package com.boublil.jwtspringsecurity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private Collection<RoleDto> roles;
}
