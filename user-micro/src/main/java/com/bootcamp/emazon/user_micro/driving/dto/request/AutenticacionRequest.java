package com.bootcamp.emazon.user_micro.driving.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutenticacionRequest {

    @NotBlank(message = "Correo es obligatorio")
    @Email(message = "Correo debe ser válido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

}
