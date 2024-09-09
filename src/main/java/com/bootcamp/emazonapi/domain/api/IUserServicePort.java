package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IUserServicePort {

    String registrarUsuario(User user);

    Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest);

    PagedResponse<User> listarUsuarios(int page, int size, String order);
}

