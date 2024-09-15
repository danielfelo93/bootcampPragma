package com.bootcamp.emazon.transaccion_micro.domain.service;

import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.LimitExceededException;

import static java.util.Objects.requireNonNull;

public class Suministro {
    private final long id;
    private final String nombre;
    private Articulo articulo;
    int cantidad;

    public Suministro(long id, String nombre, Articulo articulo, int cantidad) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
        }

        if (nombre.trim().length() > ConstantesDominio.MAX_NOMBRE_TAMANO) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE);
        }

        //Asignacion de variables
        this.id = id;
        this.nombre = requireNonNull(nombre, ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
        this.cantidad = cantidad;
        this.articulo = articulo;
    }


    public long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    
}
