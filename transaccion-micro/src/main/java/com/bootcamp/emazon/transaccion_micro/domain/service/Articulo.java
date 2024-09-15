package com.bootcamp.emazon.transaccion_micro.domain.service;



import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.LimitExceededException;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class Articulo {
    private final long id;
    private final String nombre;
    private final String descripcion;
    private final int cantidad;
    private final BigDecimal precio;

    public Articulo(long id, String nombre, String descripcion, int cantidad, BigDecimal precio) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
        }

        if (nombre.trim().length() > ConstantesDominio.MAX_NOMBRE_TAMANO) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE);
        }

        if (descripcion == null || descripcion.isEmpty()) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE);
        }

        if (descripcion.length() > ConstantesDominio.MAX_DESCRIPCION_TAMANO) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE);
        }

        //Asignacion de variables
        this.id = id;
        this.nombre = requireNonNull(nombre, ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
        this.descripcion = requireNonNull(descripcion, ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE);
        this.cantidad = cantidad;
        this.precio = requireNonNull(precio, ConstantesDominio.CAMPO_PRECIO_NULL_MENSAJE);
    }


    public long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }


    public BigDecimal getPrecio() {
        return precio;
    }


    
    
}
