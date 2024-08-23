package com.bootcamp.emazonapi.domain.api.usecase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.domain.exception.CharacterLimitExceededException;
import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCaseTest {

    @Mock
    private ICategoriaPersistencePort categoriaPersistencePort;

    @InjectMocks
    private CategoriaUseCase categoriaUseCase;

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
