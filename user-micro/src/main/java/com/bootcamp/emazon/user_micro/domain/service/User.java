package com.bootcamp.emazon.user_micro.domain.service;


import com.bootcamp.emazon.user_micro.config.security.UserRole;
import com.bootcamp.emazon.user_micro.domain.exception.InvalidDataException;

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
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;

        validate();
    }

    // Validaciones
    public void validate() {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(ConstantesDominio.FECHANACIMIENTO_NULL_MENSAJE);
        }
        if (fechaNacimiento.isAfter(LocalDate.now().minusYears(ConstantesDominio.MIN_EDAD))) {
            throw new InvalidDataException(ConstantesDominio.FECHANACIMIENTO_EDAD_MENOR_MENSAJE);
        }
        // Validaciones de otros campos
        if (!correo.matches(ConstantesDominio.CORREO_REGEX)) {
            throw new InvalidDataException(ConstantesDominio.CORREO_INVALIDO_MENSAJE);
        }
        if (!celular.matches(ConstantesDominio.CELULAR_REGEX)) {
            throw new InvalidDataException(ConstantesDominio.CELULAR_INVALIDO_MENSAJE);
        }
        if (!documentoDeIdentidad.matches(ConstantesDominio.DOCUMENTO_REGEX)) {
            throw new InvalidDataException(ConstantesDominio.DOCUMENTO_INVALIDO_MENSAJE);
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

    public void setRol(UserRole rol) { this.rol = rol; }
}