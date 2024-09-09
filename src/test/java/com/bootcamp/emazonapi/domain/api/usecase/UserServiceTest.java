package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.config.security.JwtService;
import com.bootcamp.emazonapi.config.security.UserRole;
import com.bootcamp.emazonapi.domain.exception.InvalidDataException;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;  // Mock PasswordEncoder

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

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

        userPersistencePort = mock(IUserPersistencePort.class);
        passwordEncoder = new BCryptPasswordEncoder(); // Usa una instancia real de PasswordEncoder
        userService = new UserService(userPersistencePort, passwordEncoder, jwtService, authenticationManager);
    }

    /*@Test
    void testValidUserRegistration() {
        // Configuración del mock para el metodo guardarUsuario
        when(userPersistencePort.guardarUsuario(any(User.class))).thenReturn(user1);

        // When
        String registeredUser = userService.registrarUsuario(user1);

        / Then
        assertNotNull(registeredUser);
        assertEquals("Daniel", registeredUser.getNombre());
        assertEquals("Londono", registeredUser.getApellido());
        assertEquals("8602879932", registeredUser.getDocumentoDeIdentidad());
        assertEquals("+290000000000", registeredUser.getCelular());
        assertEquals(LocalDate.of(2000, 1, 1), registeredUser.getFechaNacimiento());
        assertEquals("daniel@gmail.com", registeredUser.getCorreo());
        assertTrue(passwordEncoder.matches("plainpassword", registeredUser.getContrasena()));
        assertEquals(UserRole.AUX_BODEGA, registeredUser.getRol());
        verify(userPersistencePort, times(1)).guardarUsuario(any(User.class));*//*
    }*/

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidEmail() {
        // Given
        assertThrows(InvalidDataException.class, () -> {
            user1.setCorreo("invalid-email");
        });
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidCelular() {

        assertThrows(InvalidDataException.class, () -> {
            user1.setCelular("invalid-celular");
        });
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenInvalidDocumentoDeIdentidad() {
        assertThrows(InvalidDataException.class, () -> {
            user1.setDocumentoDeIdentidad("invalid-document");
        });

        /*// Given
        user1.setDocumentoDeIdentidad("invalid-document");

        // When & Then
        InvalidDataException thrown = assertThrows(
                InvalidDataException.class,
                user1::validate
        );

        assertEquals("Documento de identidad inválido", thrown.getMessage());*/
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenUnderage() {
        // Given
        assertThrows(InvalidDataException.class, () -> {
            user1.setFechaNacimiento(LocalDate.now().minusYears(17));
        });
    }
}