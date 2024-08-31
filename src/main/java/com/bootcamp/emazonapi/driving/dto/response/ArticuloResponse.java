package com.bootcamp.emazonapi.driving.dto.response;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class ArticuloResponse {

    private final long id;

    private final String nombre;

    private final String descripcion;

    private final int cantidad;

    private final BigDecimal precio;

    //private Long marcaId; // no un long si no un objeto marca

    //private List<Long> categoriaIds; // no un long si no una lista de objetos categoria
}
