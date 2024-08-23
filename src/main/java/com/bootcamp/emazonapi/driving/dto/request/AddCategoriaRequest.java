package com.bootcamp.emazonapi.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddCategoriaRequest {

    private String nombre;

    private String descripcion;

}