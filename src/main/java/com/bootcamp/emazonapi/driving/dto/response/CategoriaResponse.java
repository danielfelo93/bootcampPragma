package com.bootcamp.emazonapi.driving.dto.response;



import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoriaResponse {

    private final long id;

    private final String nombre;

    private final String descripcion;
}
