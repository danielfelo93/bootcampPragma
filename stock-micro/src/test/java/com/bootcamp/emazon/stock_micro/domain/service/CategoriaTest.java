package com.bootcamp.emazon.stock_micro.domain.service;

import com.bootcamp.emazon.stock_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoriaTest {

    @Test
     void testConstructor_ValidData() {
        // Arrange
        long id = 1L;
        String nombre = "Electrónica";
        String descripcion = "Artículos electrónicos";

        // Act
        Categoria categoria = new Categoria(id, nombre, descripcion);

        // Assert
        assertEquals(id, categoria.getId());
        assertEquals(nombre, categoria.getNombre());
        assertEquals(descripcion, categoria.getDescripcion());
    }

    @Test
     void testConstructor_NombreNulo() {
        // Arrange
        long id = 1L;
        String nombre = null;
        String descripcion = "Artículos electrónicos";

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
     void testConstructor_NombreVacio() {
        // Arrange
        long id = 1L;
        String nombre = "  ";
        String descripcion = "Artículos electrónicos";

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
     void testConstructor_NombreExcedeTamano() {
        // Arrange
        long id = 1L;
        String nombre = "Nombre muy largo que excede el límite de caracteres permitidos por el sistema";
        String descripcion = "Artículos electrónicos";

        // Act & Assert
        LimitExceededException exception = assertThrows(LimitExceededException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }

    @Test
     void testConstructor_DescripcionNula() {
        // Arrange
        long id = 1L;
        String nombre = "Electrónica";
        String descripcion = null;

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
     void testConstructor_DescripcionVacia() {
        // Arrange
        long id = 1L;
        String nombre = "Electrónica";
        String descripcion = "";

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
     void testConstructor_DescripcionExcedeTamano() {
        // Arrange
        long id = 1L;
        String nombre = "Electrónica";
        String descripcion = "Descripción muy larga que excede el límite de caracteres permitidos por el sistema para la descripción de una categoría.";

        // Act & Assert
        LimitExceededException exception = assertThrows(LimitExceededException.class,
                () -> new Categoria(id, nombre, descripcion));
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }

    @Test
     void testGetters() {
        // Arrange
        long id = 1L;
        String nombre = "Electrónica";
        String descripcion = "Artículos electrónicos";

        // Act
        Categoria categoria = new Categoria(id, nombre, descripcion);

        // Assert
        assertEquals(id, categoria.getId(), "El ID del objeto Categoria no coincide con el esperado.");
        assertEquals(nombre, categoria.getNombre(), "El nombre del objeto Categoria no coincide con el esperado.");
        assertEquals(descripcion, categoria.getDescripcion(), "La descripción del objeto Categoria no coincide con la esperada.");
    }
}
