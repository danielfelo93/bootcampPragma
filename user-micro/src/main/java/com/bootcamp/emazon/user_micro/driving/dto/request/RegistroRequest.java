package com.bootcamp.emazonapi.driving.dto.request;

import com.bootcamp.emazonapi.config.security.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegistroRequest {

    @NotBlank(message = "Nombre es obligatorio")
    @Size(min = 2, max = 50)
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    @Size(min = 2, max = 50)
    private String apellido;

    @NotBlank(message = "Documento de identidad es obligatorio")
    @Pattern(regexp = "\\d+", message = "Documento de identidad debe ser numérico")
    @Size(min = 7, max = 13)
    private String documentoDeIdentidad;

    @NotBlank(message = "Celular es obligatorio")
    @Size(max = 13, message = "Celular debe tener un máximo de 13 caracteres")
    @Pattern(regexp = "\\+?\\d*", message = "Celular puede contener solo números y el símbolo +")
    private String celular;

    @NotBlank(message = "Fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "Correo es obligatorio")
    @Email(message = "Correo debe ser válido")
    private String correo;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasena;

}
