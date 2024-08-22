package com.bootcamp.emazonAPI.config;

public class Constants {

    private Constants() { throw new IllegalStateException("Utility class"); }

    public static final String DATOS_NO_ENCONTRADOS_EXCEPCION_MENSAJE = "No se encontraron datos en la base de datos";

    public static final String ELEMENTO_NO_ENCONTRADO_EXCEPCION_MENSAJE = "The element indicated does not exist";

    public static final String CATEGORIA_YA_EXISTE_EXCEPCION_MENSAJE = "Ya existe una categoría con ese nombre";

    public static final String CAMPO_VACIO_EXCEPCION_MENSAJE = "Este campo no puede estar vacío";

}
