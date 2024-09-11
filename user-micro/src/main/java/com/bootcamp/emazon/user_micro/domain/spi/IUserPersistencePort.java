package com.bootcamp.emazonapi.domain.spi;

import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IUserPersistencePort {

    User guardarUsuario(User user);

    Optional<User> encontrarPorCorreo(String correo);
    boolean existsByCorreo(String correo);
    PagedResponse<User> listarUsuarios(int page, int size, String order);


}

