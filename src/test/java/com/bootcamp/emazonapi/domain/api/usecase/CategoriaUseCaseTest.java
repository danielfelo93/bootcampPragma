package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.domain.exception.CharacterLimitExceededException;
import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;

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
class CategoriaUseCaseTest {

    @Mock
    private ICategoriaPersistencePort categoriaPersistencePort;

    @InjectMocks
    private CategoriaUseCase categoriaUseCase;

    private Categoria categoria1;
    private Categoria categoria2;
    private Categoria categoria3;

    @BeforeEach
    void setUp() {
        categoria1 = new Categoria(1L, "Zapatos", "Descripción de Zapatos");
        categoria2 = new Categoria(2L, "Ropa", "Descripción de Ropa");
        categoria2 = new Categoria(3L, "Accesorios", "Descripción de Accesorios");
    }

    @Test
    void shouldReturnCategoriasSuccessfullyById() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        when(categoriaPersistencePort.listarCategorias(0, 3, "")).thenReturn(categorias);

        // Act
        List<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "");

        // Assert
        assertEquals(categorias, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "");
    }

    @Test
    void shouldReturnCategoriasSuccessfullyByNombreAsc() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        when(categoriaPersistencePort.listarCategorias(0, 3, "asc")).thenReturn(categorias);

        // Act
        List<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "asc");

        // Assert
        assertEquals(categorias, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "asc");
    }

    @Test
    void shouldReturnCategoriasSuccessfullyByNombreDesc() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        when(categoriaPersistencePort.listarCategorias(0, 3, "desc")).thenReturn(categorias);

        // Act
        List<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "desc");

        // Assert
        assertEquals(categorias, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "desc");
    }

    @Test
    void shouldReturnEmptyListWhenNoCategoriasFound() {
        // Arrange
        when(categoriaPersistencePort.listarCategorias(0, 3, "")).thenReturn(Arrays.asList());

        // Act
        List<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "");

        // Assert
        assertEquals(0, result.size());
        verify(categoriaPersistencePort).listarCategorias(0, 3, "");
    }

    @Test
    void shouldAddCategoriaSuccessfully() {

        //Arrange
        Categoria categoria = new Categoria(1L,"EjemploCategoria","Ejemplo de una descripcion válida");

        // Act
        categoriaUseCase.guardarCategoria(categoria);

        // Assert
        verify(categoriaPersistencePort, times(1)).guardarCategoria(categoria);
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenNombreIsEmpty() {
        // Arrange
        assertThrows(EmptyFieldException.class, () -> {
            new Categoria(1L, "", "Descripción válida");
        });
    }
    @Test
    void shouldThrowEmptyFieldExceptionWhenDescripcionIsEmpty() {
        // Arrange
        assertThrows(EmptyFieldException.class, () -> {
            new Categoria(1L, "EjemploCategoria", "");
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenNombreExceedsLength() {
        // Arrange
        assertThrows(CharacterLimitExceededException.class, () -> {
            new Categoria(1L, "Nombre que excede los 50 caracteres permitidos por la validación", "Ejemplo de una descripcion válida");
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenDescripcionExceedsLength() {
        // Arrange
        assertThrows(CharacterLimitExceededException.class, () -> {
            new Categoria(1L, "EjemploCategoria", "Ejemplo de una descripcion Descripción que excede los 90 caracteres permitidos por la validación y debería devolver un error");
        });
    }
}
