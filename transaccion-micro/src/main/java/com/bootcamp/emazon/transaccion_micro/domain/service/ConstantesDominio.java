package com.bootcamp.emazon.transaccion_micro.domain.service;

public final class ConstantesDominio {

    private ConstantesDominio() { throw new IllegalStateException("Utility class"); }

    public static final String ERROR_EN_STOCK = "Error al actualizar suministros en stock-micro";
    public static final String CAMPO_ID_VACIO = "El ID del artículo no puede ser nulo o vacío";
    public static final String CAMPO_CANTIDAD_POSITIVO = "La cantidad debe ser mayor a 0";

}
