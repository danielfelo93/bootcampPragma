package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.config.security.JwtService;
import com.bootcamp.emazonapi.config.security.UserRole;
import com.bootcamp.emazonapi.domain.exception.InvalidDataException;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import com.bootcamp.emazonapi.driving.dto.request.AutenticacionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 = new User(
                1L,
                "Daniel",
                "Londono",
                "8602879932",
                "+290000000000",
                LocalDate.of(2000, 1, 1),
                "daniel@gmail.com",
                "plainpassword",
                UserRole.AUX_BODEGA
        );

        lenient().when(passwordEncoder.encode(anyString())).thenReturn("encodedpassword");
        userService = new UserService(userPersistencePort, passwordEncoder, jwtService, authenticationManager);
    }

    @Test
    void testValidUserRegistration() {
        // Configuración del mock para el metodo guardarUsuario
        when(userPersistencePort.guardarUsuario(any(User.class))).thenReturn(user1);
        when(jwtService.getToken(anyString())).thenReturn("mocked-jwt-token");

        // When
        String registeredUser = userService.registrarUsuario(user1);

        // Then
        assertNotNull(registeredUser);
        assertEquals("mocked-jwt-token", registeredUser);
        verify(userPersistencePort, times(1)).guardarUsuario(any(User.class));
        verify(passwordEncoder, times(1)).encode(anyString());
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidEmail() {
        // Given
        assertThrows(InvalidDataException.class, () -> user1.setCorreo("invalid-email"));
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidCelular() {

        assertThrows(InvalidDataException.class, () -> user1.setCelular("invalid-celular"));
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidDocumentoDeIdentidad() {
        assertThrows(InvalidDataException.class, () -> user1.setDocumentoDeIdentidad("invalid-document"));
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenUnderage() {
        // Given
        assertThrows(InvalidDataException.class, () -> user1.setFechaNacimiento(LocalDate.now().minusYears(17)));
    }

    @Test
    void shouldFailAuthenticationForInvalidCredentials() {
        // Given
        AutenticacionRequest invalidRequest = new AutenticacionRequest("daniel@gmail.com", "wrongpassword");

        // When
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Credenciales inválidas") {});
        assertThrows(Exception.class, () -> userService.autenticarUsuario(invalidRequest));

        // Then
        verify(authenticationManager, times(1)).authenticate(any(Authentication.class));
        verify(jwtService, never()).getToken(any());
    }
}