package com.bootcamp.emazonapi.domain.service;

public final class ConstantesDominio {

    private ConstantesDominio() { throw new IllegalStateException("Utility class"); }

    public static final String CAMPO_NOMBRE_NULL_MENSAJE = "El campo nombre no puede sel nulo";
    public static final String CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE = "El campo nombre no puede exceder los 50 caracteres";
    public static final String CAMPO_DESCRIPCION_NULL_MENSAJE = "El campo descripción no puede sel nulo";
    public static final String CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE = "El campo descripción no puede exceder los 90 caracteres";
    public static final String CAMPO_CANTIDAD_NEGATIVE_MENSAJE = "El campo cantidad no puede sel negativo";
    public static final String CAMPO_PRECIO_NULL_MENSAJE = "El campo precio no puede sel nulo";
    public static final String CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE = "El artículo debe tener entre 1 y 3 categorías.";
    public static final String CAMPO_CATEGORIA_DUPLICADA_MENSAJE = "No se permiten categorías duplicadas.";
    public static final String FECHANACIMIENTO_EDAD_MENOR_MENSAJE = "El usuario debe ser mayor de edad.";

    public static final int MAX_NOMBRE_TAMANO = 50;
    public static final int MAX_DESCRIPCION_TAMANO = 90;
    public static final int MAX_DESCRIPCIONMARCA_TAMANO = 120;
    public static final int MAX_CATEGORIAS_TAMANO = 3;
    public static final int MIN_EDAD = 18;
}
