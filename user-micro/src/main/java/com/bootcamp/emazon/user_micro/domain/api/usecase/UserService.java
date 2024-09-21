package com.bootcamp.emazon.user_micro.domain.api.usecase;

import com.bootcamp.emazon.user_micro.domain.exception.InvalidTokenException;
import com.bootcamp.emazon.user_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.user_micro.domain.spi.IUserPersistencePort;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.user_micro.config.security.JwtService;
import com.bootcamp.emazon.user_micro.domain.api.IUserServicePort;
import com.bootcamp.emazon.user_micro.domain.exception.UserAlreadyExistsException;
import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driving.dto.request.AutenticacionRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
            throw new UserAlreadyExistsException(ConstantesDominio.CORREO_REGISTRADO_MENSAJE);
        }
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        userPersistencePort.guardarUsuario(user);
        return jwtService.getToken(user.getCorreo());
    }

    @Override
    public Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    autenticacionRequest.getCorreo(),
                    autenticacionRequest.getContrasena()));
        } catch (Exception e) {
            throw new InvalidTokenException(ConstantesDominio.AUTENTICACION_FALLIDA);
        }

        User user = userPersistencePort.encontrarPorCorreo(autenticacionRequest.getCorreo())
                .orElseThrow(() -> new RuntimeException(ConstantesDominio.USUARIO_NO_ENCONTRADO));
        String token = jwtService.getToken(user.getCorreo());
        return Optional.of(token);
    }
}