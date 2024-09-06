package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.config.security.UserRole;
import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driving.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {


    // Mapeo de User a RegistroResponse
    @Mapping(source = "rol", target = "rol", qualifiedByName = "userRoleToString")
    RegistroResponse userToRegistroResponse(User user);
    PagedResponse<RegistroResponse> registroToResponseList(PagedResponse<User> users);

    // Mapeo de token a AutenticacionResponse
    @Mapping(source = "token", target = "token")
    AutenticacionResponse userToAutenticacionResponse(String token);

    @Named("userRoleToString")
    default String mapUserRoleToString(UserRole userRole) {
        return userRole != null ? userRole.name() : null;
    }

}


