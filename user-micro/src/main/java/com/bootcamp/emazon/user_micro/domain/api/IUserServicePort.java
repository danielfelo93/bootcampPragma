package com.bootcamp.emazon.user_micro.domain.api;


import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IUserServicePort {

    String registrarUsuario(User user);

    Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest);

    PagedResponse<User> listarUsuarios(int page, int size, String order);
}

