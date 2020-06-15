package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.RoleDto;
import com.boublil.jwtspringsecurity.dto.RoleDto.RoleDtoBuilder;
import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.dto.UserDto.UserDtoBuilder;
import com.boublil.jwtspringsecurity.model.AppRole;
import com.boublil.jwtspringsecurity.model.AppUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T12:56:08+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(AppUser user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.roles( appRoleCollectionToRoleDtoCollection( user.getRoles() ) );

        return userDto.build();
    }

    @Override
    public List<UserDto> usersToUserDtos(List<AppUser> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( AppUser appUser : users ) {
            list.add( userToUserDto( appUser ) );
        }

        return list;
    }

    protected RoleDto appRoleToRoleDto(AppRole appRole) {
        if ( appRole == null ) {
            return null;
        }

        RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.role( appRole.getRole() );

        return roleDto.build();
    }

    protected Collection<RoleDto> appRoleCollectionToRoleDtoCollection(Collection<AppRole> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<RoleDto> collection1 = new ArrayList<RoleDto>( collection.size() );
        for ( AppRole appRole : collection ) {
            collection1.add( appRoleToRoleDto( appRole ) );
        }

        return collection1;
    }
}
