package com.bootcamp.emazonapi.driving.dto.response;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ArticuloResponse {

    private final long id;

    private final String nombre;

    private final String descripcion;

    private final int cantidad;

    private final BigDecimal precio;

    private ArticuloMarcaResponse marca;

    private Set<ArticuloCategoriaResponse> categorias;
}
