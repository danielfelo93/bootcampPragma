package com.bootcamp.emazonapi.domain.service;
import com.bootcamp.emazonapi.config.security.UserRole;
import com.bootcamp.emazonapi.domain.exception.InvalidDataException;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

public class User {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoDeIdentidad;
    private String celular;
    private LocalDate fechaNacimiento;
    private String correo;
    private String contrasena;  // Clave encriptada con bcrypt
    private UserRole rol;

    // Constructor
    public User(Long id, String nombre, String apellido, String documentoDeIdentidad,
                String celular, LocalDate fechaNacimiento, String correo,
                String contrasena, UserRole rol) {


        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoDeIdentidad = documentoDeIdentidad;
        this.celular = celular;
        System.out.println("Fecha de Nacimiento antes de asignar: " + fechaNacimiento);
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = UserRole.AUX_BODEGA;

        validate();
    }

    // Validaciones
    public void validate() {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidDataException("El usuario debe ser mayor de edad");
        }
        // Validaciones de otros campos
        if (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidDataException("Correo inválido");
        }
        if (!celular.matches("\\+\\d{10,13}")) {
            throw new InvalidDataException("Número de celular inválido");
        }
        if (!documentoDeIdentidad.matches("\\d{10}")) {
            throw new InvalidDataException("Documento de identidad inválido");
        }
    }

    /// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumentoDeIdentidad() {
        return documentoDeIdentidad;
    }

    public void setDocumentoDeIdentidad(String documentoDeIdentidad) {
        this.documentoDeIdentidad = documentoDeIdentidad;
        validate();
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
        validate();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        validate();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
        validate();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public UserRole getRol() {
        return rol;
    }

    public void setRol(UserRole rol) {
        this.rol = rol;
    }
}