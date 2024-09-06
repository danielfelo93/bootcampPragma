package com.bootcamp.emazonapi.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutenticacionResponse {

    private String token; // Aquí puedes incluir un token JWT si es necesario
    //private RegistroResponse user;

}
