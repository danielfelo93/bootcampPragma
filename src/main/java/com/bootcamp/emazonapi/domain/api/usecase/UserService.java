package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.config.security.JwtService;
import com.bootcamp.emazonapi.domain.api.IUserServicePort;
import com.bootcamp.emazonapi.domain.exception.UserAlreadyExistsException;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import com.bootcamp.emazonapi.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserService(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public PagedResponse<User> listarUsuarios(int page, int size, String order){
        return userPersistencePort.listarUsuarios(page,size,order);
    }

    @Override
    public String registrarUsuario(User user) {
        if (userPersistencePort.existsByCorreo(user.getCorreo())) {
            throw new UserAlreadyExistsException("El correo electrónico ya está registrado.");
        }
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        userPersistencePort.guardarUsuario(user);

        return jwtService.getToken(user.getCorreo());
    }

    @Override
    public Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                autenticacionRequest.getCorreo(),
                autenticacionRequest.getContrasena()));
                User user = userPersistencePort.encontrarPorCorreo(autenticacionRequest.getCorreo())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.getToken(user.getCorreo());
        return Optional.of(token);

        /*// Obtener el usuario por correo
        User user = userPersistencePort.encontrarPorCorreo(autenticacionRequest.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar la contraseña
        if (passwordEncoder.matches(autenticacionRequest.getContrasena(), user.getContrasena())) {

            String token = jwtService.getToken(user.getCorreo());

            return Optional.of(token);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }*/
    }
}