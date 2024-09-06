package com.bootcamp.emazonapi.driving.dto.request;

import com.bootcamp.emazonapi.config.security.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
