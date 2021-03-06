package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.RoleDto;
import com.boublil.jwtspringsecurity.dto.TaskDto;
import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.model.AppRole;
import com.boublil.jwtspringsecurity.model.AppUser;
import com.boublil.jwtspringsecurity.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToRoleDto(AppRole role);
    List<RoleDto> rolesToRoleDtos(List<AppRole> roles);
}
