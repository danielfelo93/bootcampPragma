package com.bootcamp.emazonapi.domain.api.usecase;

//import com.bootcamp.emazonapi.config.security.JwtUtil;
import com.bootcamp.emazonapi.domain.api.IUserServicePort;
import com.bootcamp.emazonapi.domain.exception.InvalidAgeException;
import com.bootcamp.emazonapi.domain.service.ConstantesDominio;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import com.bootcamp.emazonapi.driving.dto.request.AutenticacionRequest;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
//import io.jsonwebtoken.Jwts;
import java.time.Period;
import java.time.LocalDate;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    //private final JwtUtil jwtUtil;

    public UserService(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder/*, JwtUtil jwtUtil*/) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        //this.jwtUtil = jwtUtil;
    }

    @Override
    public PagedResponse<User> listarUsuarios(int page, int size, String order){
        return userPersistencePort.listarUsuarios(page,size,order);
    }

    @Override
    public User registrarUsuario(User user) {
        // Validaciones adicionales si es necesario
        if (user.getFechaNacimiento() == null) {
                throw new InvalidAgeException(ConstantesDominio.FECHANACIMIENTO_EDAD_MENOR_MENSAJE);
            }

        int edad = Period.between(user.getFechaNacimiento(), LocalDate.now()).getYears();
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));

        if (edad >= ConstantesDominio.MIN_EDAD) {
            // Guardar el usuario en la base de datos
            userPersistencePort.guardarUsuario(user);
            return user;
        } else {
            throw new InvalidAgeException(ConstantesDominio.FECHANACIMIENTO_EDAD_MENOR_MENSAJE);
        }


    }

    @Override
    public Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest) {
        return Optional.empty();
    }

    /*@Override
    public Optional<String> autenticarUsuario(AutenticacionRequest autenticacionRequest) {
        // Obtener el usuario por correo
        User user = userPersistencePort.encontrarPorCorreo(autenticacionRequest.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar la contraseña
        if (passwordEncoder.matches(autenticacionRequest.getContrasena(), user.getContrasena())) {
            String token = jwtUtil.generateToken(user.getCorreo());

            return Optional.of(token);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }*/
}