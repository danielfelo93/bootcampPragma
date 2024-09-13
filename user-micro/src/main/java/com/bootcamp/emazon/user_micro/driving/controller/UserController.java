package com.bootcamp.emazon.user_micro.driving.controller;


import com.bootcamp.emazon.user_micro.config.security.JwtService;
import com.bootcamp.emazon.user_micro.domain.api.IUserServicePort;
import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazon.user_micro.driving.dto.request.RegistroRequest;
import com.bootcamp.emazon.user_micro.driving.dto.response.AutenticacionResponse;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.user_micro.driving.dto.response.RegistroResponse;
import com.bootcamp.emazon.user_micro.driving.mapper.IUserRequestMapper;
import com.bootcamp.emazon.user_micro.driving.mapper.IUserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final IUserServicePort userService;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<AutenticacionResponse> registrarUsuario(@RequestBody RegistroRequest registroRequest) {
        User user = userRequestMapper.registroRequestToUser(registroRequest);
        String token = userService.registrarUsuario(user);

        AutenticacionResponse autenticacionResponse = userResponseMapper.userToAutenticacionResponse(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(autenticacionResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AutenticacionResponse> autenticarUsuario(@RequestBody AutenticacionRequest autenticacionRequest) {
        Optional<String> token = userService.autenticarUsuario(autenticacionRequest);

        if (token.isPresent()) {
            AutenticacionResponse response = userResponseMapper.userToAutenticacionResponse(token.get());
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("Error en la autenticación");
        }
    }

    @Operation(summary = "Validar token JWT", description = "Valida un token JWT proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token inválido o expirado")
    })
    @GetMapping("/validate-token")
    public ResponseEntity<String> validarToken(@RequestParam("token") String token) {
        try {
            String username = jwtService.getUsernameFromToken(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Asegúrate de que el servicio de usuario pueda cargar UserDetails
            System.out.println("UserDetails: " + userDetails);
            boolean isValid = jwtService.isTokenValid(token, userDetails);
            System.out.println("Is Token Valid: " + isValid);
            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<RegistroResponse>> obtenerUsuarios(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", defaultValue = "3") int size,
                                                                           @RequestParam(value = "order", defaultValue = "") String order) {
        return ResponseEntity.ok(userResponseMapper.registroToResponseList(userService.listarUsuarios(page, size, order)));
    }
}

