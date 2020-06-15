package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.RoleDto;
import com.boublil.jwtspringsecurity.dto.RoleDto.RoleDtoBuilder;
import com.boublil.jwtspringsecurity.model.AppRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T12:56:08+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto roleToRoleDto(AppRole role) {
        if ( role == null ) {
            return null;
        }

        RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.role( role.getRole() );

        return roleDto.build();
    }

    @Override
    public List<RoleDto> rolesToRoleDtos(List<AppRole> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( roles.size() );
        for ( AppRole appRole : roles ) {
            list.add( roleToRoleDto( appRole ) );
        }

        return list;
    }
}
