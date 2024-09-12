package com.bootcamp.emazon.user_micro.driving.mapper;

import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazon.user_micro.driving.dto.request.RegistroRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



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

}

















