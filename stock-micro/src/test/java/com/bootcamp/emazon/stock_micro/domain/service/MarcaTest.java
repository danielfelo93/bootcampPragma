package com.bootcamp.emazon.stock_micro.domain.service;

import com.bootcamp.emazon.stock_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MarcaTest {

    @Test
    void testConstructor_ValidData() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        assertEquals(1L, marca.getId());
        assertEquals("MarcaValida", marca.getNombre());
        assertEquals("Descripcion valida", marca.getDescripcion());
    }

    @Test
    void testConstructor_NombreNull() {
        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Marca(1L, null, "Descripcion valida")
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_NombreEmpty() {
        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Marca(1L, "", "Descripcion valida")
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_NombreTooLong() {
        String nombreLargo = "A".repeat(ConstantesDominio.MAX_NOMBRE_TAMANO + 1);
        Exception exception = assertThrows(LimitExceededException.class, () ->
                new Marca(1L, nombreLargo, "Descripcion valida")
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionNull() {
        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Marca(1L, "MarcaValida", null)
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionEmpty() {
        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Marca(1L, "MarcaValida", "")
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionTooLong() {
        String descripcionLarga = "A".repeat(ConstantesDominio.MAX_DESCRIPCIONMARCA_TAMANO + 1);
        Exception exception = assertThrows(LimitExceededException.class, () ->
                new Marca(1L, "MarcaValida", descripcionLarga)
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }
}