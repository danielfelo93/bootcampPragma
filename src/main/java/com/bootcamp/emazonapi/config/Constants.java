package com.bootcamp.emazonapi.config;

public class Constants {

    private Constants() { throw new IllegalStateException("Utility class"); }

    public static final String DATOS_NO_ENCONTRADOS_EXCEPCION_MENSAJE = "No se encontraron datos en la base de datos";

    public static final String ELEMENTO_NO_ENCONTRADO_EXCEPCION_MENSAJE = "El elemento que buscas no existe";

    public static final String CATEGORIA_YA_EXISTE_EXCEPCION_MENSAJE = "Ya existe una categoría con ese nombre";

    public static final String MARCA_YA_EXISTE_EXCEPCION_MENSAJE = "Ya existe una marca con ese nombre";

    public static final String ARTICULO_YA_EXISTE_EXCEPCION_MENSAJE = "Ya existe un artículo con ese nombre";

    public static final String NOMBRE_VACIO_EXCEPCION_MENSAJE = "El campo nombre no puede estar vacío";

    public static final String DESCRIPCION_VACIA_EXCEPCION_MENSAJE = "El campo descripcion no puede estar vacío";

    public static final String CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE = "El artículo debe tener entre 1 y 3 categorías.";



}
