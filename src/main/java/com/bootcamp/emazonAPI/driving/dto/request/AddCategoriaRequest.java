package com.bootcamp.emazonAPI.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddCategoriaRequest {

    //private  Long id;

    private String nombre;

    private String descripcion;

}