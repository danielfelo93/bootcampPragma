package com.bootcamp.emazon.stock_micro.domain.api.usecase;

import com.bootcamp.emazon.stock_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
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

    private void assertPagedResponseContentEquals(PagedResponse<Categoria> expected, PagedResponse<Categoria> actual) {
        assertEquals(expected.getContent(), actual.getContent(), "Contenido no coincide");
    }

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
        PagedResponse<Categoria> pagedResponse = new PagedResponse<>(categorias, 0, 3, 3, 1, true);
        when(categoriaPersistencePort.listarCategorias(0, 3, "")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "");
    }

    @Test
    void shouldReturnCategoriasSuccessfullyByNombreAsc() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        PagedResponse<Categoria> pagedResponse = new PagedResponse<>(categorias, 0, 3, 3, 1, true);
        when(categoriaPersistencePort.listarCategorias(0, 3, "asc")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "asc");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "asc");
    }

    @Test
    void shouldReturnCategoriasSuccessfullyByNombreDesc() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        PagedResponse<Categoria> pagedResponse = new PagedResponse<>(categorias, 0, 3, 3, 1, true);
        when(categoriaPersistencePort.listarCategorias(0, 3, "desc")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "desc");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "desc");
    }

    @Test
    void shouldReturnEmptyListWhenNoCategoriasFound() {
        // Arrange
        PagedResponse<Categoria> pagedResponse = new PagedResponse<>(Arrays.asList(), 0, 3, 0, 0, true);
        when(categoriaPersistencePort.listarCategorias(0, 3, "")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Categoria> result = categoriaUseCase.listarCategorias(0, 3, "");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(categoriaPersistencePort).listarCategorias(0, 3, "");
    }

    @Test
    void shouldAddCategoriaSuccessfully() {

        //Arrange
        Categoria categoria = new Categoria(1L,"EjemploCategoria","Ejemplo de una descripcion válida");
        String token = "Bearer some-jwt-token";

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
        assertThrows(LimitExceededException.class, () -> {
            new Categoria(1L, "Nombre que excede los 50 caracteres permitidos por la validación", "Ejemplo de una descripcion válida");
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenDescripcionExceedsLength() {
        // Arrange
        assertThrows(LimitExceededException.class, () -> {
            new Categoria(1L, "EjemploCategoria", "Ejemplo de una descripcion Descripción que excede los 90 caracteres permitidos por la validación y debería devolver un error");
        });
    }
}
