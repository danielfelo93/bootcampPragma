package com.bootcamp.emazon.stock_micro.domain.service;

import com.bootcamp.emazon.stock_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticuloTest {

    @Test
    void testConstructor_ValidData() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Articulo articulo = new Articulo(1L, "ArticuloValido", "Descripcion valida", 10, BigDecimal.valueOf(19.99), marca, categorias);

        assertEquals(1L, articulo.getId());
        assertEquals("ArticuloValido", articulo.getNombre());
        assertEquals("Descripcion valida", articulo.getDescripcion());
        assertEquals(10, articulo.getCantidad());
        assertEquals(BigDecimal.valueOf(19.99), articulo.getPrecio());
        assertEquals(marca, articulo.getMarca());
        assertEquals(categorias, articulo.getCategorias());
    }

    BigDecimal precio =new BigDecimal("19.99");

    @Test
    void testConstructor_NombreNull() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Articulo(1L, null, "Descripcion valida", 10, precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_NombreEmpty() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Articulo(1L, "", "Descripcion valida", 10,precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_NombreTooLong() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        String nombreLargo = "A".repeat(ConstantesDominio.MAX_NOMBRE_TAMANO + 1);
        Exception exception = assertThrows(LimitExceededException.class, () ->
                new Articulo(1L, nombreLargo, "Descripcion valida", 10, precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionNull() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Articulo(1L, "ArticuloValido", null, 10, precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionEmpty() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Exception exception = assertThrows(EmptyFieldException.class, () ->
                new Articulo(1L, "ArticuloValido", "", 10, precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_DescripcionTooLong() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        String descripcionLarga = "A".repeat(ConstantesDominio.MAX_DESCRIPCION_TAMANO + 1);
        Exception exception = assertThrows(LimitExceededException.class, () ->
                new Articulo(1L, "ArticuloValido", descripcionLarga, 10, precio, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE, exception.getMessage());
    }

    @Test
    void testConstructor_PrecioNull() {
        Marca marca = new Marca(1L, "MarcaValida", "Descripcion valida");
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "Categoria1", "Descripcion Categoria1"));

        Exception exception = assertThrows(NullPointerException.class, () ->
                new Articulo(1L, "ArticuloValido", "Descripcion valida", 10, null, marca, categorias)
        );
        assertEquals(ConstantesDominio.CAMPO_PRECIO_NULL_MENSAJE, exception.getMessage());
    }
}
