package com.bootcamp.emazonapi.driving.mapper;


import com.bootcamp.emazonapi.config.security.UserRole;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazonapi.driving.dto.request.RegistroRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    // Mapeo de RegistroRequest a User
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    User registroRequestToUser(RegistroRequest registroRequest);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nombre", ignore = true)
    @Mapping(target = "apellido", ignore = true)
    @Mapping(target = "documentoDeIdentidad", ignore = true)
    @Mapping(target = "celular", ignore = true)
    @Mapping(target = "fechaNacimiento", ignore = true)
    @Mapping(target = "rol", ignore = true)
    User autenticacionRequestToUser(AutenticacionRequest autenticacionRequest);

    /*
    @Mapping(source = "rol",target = "rol", qualifiedByName = "stringToUserRole")
    User registroRequestToUser(RegistroRequest registroRequest);


    @Named("stringToUserRole")
    default UserRole mapStringToUserRole(String rol) {
        try {
            return UserRole.valueOf(rol);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + rol);
        }
    }

    @Named("userRoleToString")
    default String mapUserRoleToString(UserRole userRole) {
        return userRole != null ? userRole.name() : null;
    }*/
}
