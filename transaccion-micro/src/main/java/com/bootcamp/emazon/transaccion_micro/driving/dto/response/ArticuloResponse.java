package com.bootcamp.emazon.transaccion_micro.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ArticuloResponse {

    private final long id;

    private final String nombre;

    private final String descripcion;

    private final int cantidad;

    private final BigDecimal precio;

}
