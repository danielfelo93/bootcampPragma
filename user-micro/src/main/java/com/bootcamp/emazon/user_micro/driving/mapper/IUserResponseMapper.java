package com.bootcamp.emazon.user_micro.driving.mapper;

import com.bootcamp.emazon.user_micro.config.security.UserRole;
import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.response.AutenticacionResponse;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.user_micro.driving.dto.response.RegistroResponse;
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


