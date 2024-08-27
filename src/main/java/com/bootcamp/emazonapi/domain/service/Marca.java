package com.bootcamp.emazonapi.domain.service;

import com.bootcamp.emazonapi.domain.exception.CharacterLimitExceededException;
import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;

import static java.util.Objects.requireNonNull;

public class Marca {
    private final long id;
    private final String nombre;
    private final String descripcion;


    public Marca(long id, String nombre, String descripcion) {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new EmptyFieldException(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
            }
    
            if (nombre.trim().length() > ConstantesDominio.MAX_NOMBRE_TAMANO) {
                throw new CharacterLimitExceededException(ConstantesDominio.CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE);
            }
    
            if (descripcion == null || descripcion.isEmpty()) {
                throw new EmptyFieldException(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE);
            }
    
            if (descripcion.length() > ConstantesDominio.MAX_DESCRIPCIONMARCA_TAMANO) {
                throw new CharacterLimitExceededException(ConstantesDominio.CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE);
            }

        this.id = id;
        this.nombre = requireNonNull(nombre, ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE); 
        this.descripcion = requireNonNull(descripcion, ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
    }


    public long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    
    
}
