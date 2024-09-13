package com.bootcamp.emazon.user_micro.domain.spi;


import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IUserPersistencePort {

    User guardarUsuario(User user);

    Optional<User> encontrarPorCorreo(String correo);
    boolean existsByCorreo(String correo);
    PagedResponse<User> listarUsuarios(int page, int size, String order);

    Optional<Object> findByCorreo(String username);
}

