package com.bootcamp.emazon.transaccion_micro.domain.service;

public final class ConstantesDominio {

    private ConstantesDominio() { throw new IllegalStateException("Utility class"); }

    public static final String CAMPO_NOMBRE_NULL_MENSAJE = "El campo nombre no puede sel nulo";
    public static final String CAMPO_NOMBRE_TAMANO_EXCEDIDO_MENSAJE = "El campo nombre no puede exceder los 50 caracteres";
    public static final String CAMPO_DESCRIPCION_NULL_MENSAJE = "El campo descripción no puede sel nulo";
    public static final String CAMPO_DESCRIPCION_TAMANO_EXCEDIDO_MENSAJE = "El campo descripción no puede exceder los 90 caracteres";
    public static final String CAMPO_PRECIO_NULL_MENSAJE = "El campo precio no puede sel nulo";
    public static final String CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE = "El artículo debe tener entre 1 y 3 categorías.";
    public static final String CAMPO_CATEGORIA_DUPLICADA_MENSAJE = "No se permiten categorías duplicadas.";

    public static final int MAX_NOMBRE_TAMANO = 50;
    public static final int MAX_DESCRIPCION_TAMANO = 90;
    public static final int MAX_CATEGORIAS_TAMANO = 3;
}
