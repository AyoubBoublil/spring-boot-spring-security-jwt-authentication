package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.model.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(AppUser user);
    List<UserDto> usersToUserDtos(List<AppUser> users);
}
