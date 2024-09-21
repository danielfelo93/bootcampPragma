package com.bootcamp.emazon.transaccion_micro.domain.service;

import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.InvalidDataException;

import java.time.LocalDateTime;

public class Suministro {
    private final Long id;
    private final Long articuloId;
    private LocalDateTime fechaDeIngreso;
    private Integer cantidad;

    public Suministro(Long id, Long articuloId, Integer cantidad) {
        // Validar que el ID del artículo no sea nulo
        if (articuloId == null) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_ID_VACIO);
        }

        // Validar que la cantidad no sea nula ni menor o igual a 0
        if ((cantidad == null) || (cantidad <= 0)) {
            throw new InvalidDataException(ConstantesDominio.CAMPO_CANTIDAD_POSITIVO);
        }

        //Asignacion de variables
        this.id = id;
        this.articuloId = articuloId;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setFechaDeIngreso(LocalDateTime fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Long getArticuloId() {
        return articuloId;
    }

    public LocalDateTime getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
