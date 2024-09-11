package com.bootcamp.emazonapi.driven.adapter;

import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driven.entity.UserEntity;
import com.bootcamp.emazonapi.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazonapi.driven.mapper.UserEntityMapper;
import com.bootcamp.emazonapi.driven.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserAdaptadorTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userMapper;

    @InjectMocks
    private UserAdaptador userAdaptador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void testGuardarUsuario() {
        // Datos de prueba
        User user = new User(1L, "Nombre", "Apellido", "12345678", "1234567890", LocalDate.now(), "correo@example.com", "contrasena", true);
        user.setId(1L);
        user.setCorreo("test@example.com");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setCorreo("test@example.com");

        // Comportamiento simulado
        when(userRepository.findByCorreo(user.getCorreo())).thenReturn(Optional.empty());
        when(userMapper.userToUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // Ejecutar método
        userAdaptador.guardarUsuario(user);

        // Verificar interacciones
        verify(userRepository).findByCorreo(user.getCorreo());
        verify(userMapper).userToUserEntity(user);
        verify(userRepository).save(userEntity);
    }

    @Test
    void testGuardarUsuario_UsuarioYaExiste() {
        // Datos de prueba
        User user = new User(1L, "Nombre", "Apellido", "12345678", "1234567890", LocalDate.now(), "correo@example.com", "contrasena", true);
        user.setCorreo("test@example.com");

        UserEntity userEntity = new UserEntity();
        userEntity.setCorreo("test@example.com");

        // Comportamiento simulado
        when(userRepository.findByCorreo(user.getCorreo())).thenReturn(Optional.of(userEntity));

        // Ejecutar y verificar excepciones
        ProductAlreadyExistsException thrown = assertThrows(
                ProductAlreadyExistsException.class,
                () -> userAdaptador.guardarUsuario(user),
                "Expected guardarUsuario() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("El usuario con el correo ya existe."));
    }*/
}
