package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.exception.CharacterLimitExceededException;
import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;
import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarcaUseCaseTest {

    @Mock
    private IMarcaPersistencePort marcaPersistencePort;

    @InjectMocks
    private MarcaUseCase marcaUseCase;

    private Marca marca1;
    private Marca marca2;
    private Marca marca3;

    @BeforeEach
    void setUp() {
        marca1 = new Marca(1L, "Zapatos", "Descripción de Zapatos");
        marca2 = new Marca(2L, "Ropa", "Descripción de Ropa");
        marca3 = new Marca(3L, "Accesorios", "Descripción de Accesorios");
    }

    @Test
    void shouldReturnMarcasSuccessfullyById() {
        // GIVEN
        List<Marca> marcas = Arrays.asList(marca1, marca2, marca3);
        when(marcaPersistencePort.listarMarcas(0, 3, "")).thenReturn(marcas);

        // WHEN
        List<Marca> result = marcaUseCase.listarMarcas(0, 3, "");

        // THEN
        assertEquals(marcas, result);
        verify(marcaPersistencePort).listarMarcas(0, 3, "");
    }

    @Test
    void shouldReturnMarcasSuccessfullyByNombreAsc() {
        // GIVEN
        List<Marca> marcas = Arrays.asList(marca1, marca2, marca3);
        when(marcaPersistencePort.listarMarcas(0, 3, "asc")).thenReturn(marcas);

        // WHEN
        List<Marca> result = marcaUseCase.listarMarcas(0, 3, "asc");

        // THEN
        assertEquals(marcas, result);
        verify(marcaPersistencePort).listarMarcas(0, 3, "asc");
    }

    @Test
    void shouldReturnMarcasSuccessfullyByNombreDesc() {
        // GIVEN
        List<Marca> marcas = Arrays.asList(marca1, marca2, marca3);
        when(marcaPersistencePort.listarMarcas(0, 3, "desc")).thenReturn(marcas);

        // WHEN
        List<Marca> result = marcaUseCase.listarMarcas(0, 3, "desc");

        // THEN
        assertEquals(marcas, result);
        verify(marcaPersistencePort).listarMarcas(0, 3, "desc");
    }

    @Test
    void shouldReturnEmptyListWhenNoMarcasFound() {
        // GIVEN
        when(marcaPersistencePort.listarMarcas(0, 3, "")).thenReturn(Arrays.asList());

        // WHEN
        List<Marca> result = marcaUseCase.listarMarcas(0, 3, "");

        // THEN
        assertEquals(0, result.size());
        verify(marcaPersistencePort).listarMarcas(0, 3, "");
    }

    @Test
    void shouldAddMarcaSuccessfully() {

        //GIVEN
        Marca marca = new Marca(1L,"EjemploMarca","Ejemplo de una descripcion válida");

        //WHEN
        marcaUseCase.guardarMarca(marca);

        //THEN
        verify(marcaPersistencePort, times(1)).guardarMarca(marca);
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenNombreIsEmpty() {
        // GIVEN, WHEN & THEN
        assertThrows(EmptyFieldException.class, () -> {
            new Marca(1L, "", "Descripción válida");
        });
    }
    @Test
    void shouldThrowEmptyFieldExceptionWhenDescripcionIsEmpty() {
        // GIVEN, WHEN & THEN
        assertThrows(EmptyFieldException.class, () -> {
            new Marca(1L, "EjemploMarca", "");
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenNombreExceedsLength() {
        // GIVEN, WHEN & THEN
        assertThrows(CharacterLimitExceededException.class, () -> {
            new Marca(1L, "Nombre que excede los 50 caracteres permitidos por la validación", "Ejemplo de una descripcion válida");
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenDescripcionExceedsLength() {
        // GIVEN, WHEN & THEN
        assertThrows(CharacterLimitExceededException.class, () -> {
            new Marca(1L, "EjemploMarca", "Ejemplo de una descripcion Descripción que excede los 90 caracteres permitidos por la validación y debería devolver un error");
        });
    }
}
