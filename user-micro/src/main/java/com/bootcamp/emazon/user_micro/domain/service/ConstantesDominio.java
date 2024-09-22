package com.bootcamp.emazon.user_micro.domain.service;

public final class ConstantesDominio {

    private ConstantesDominio() { throw new IllegalStateException("Utility class"); }

    public static final String FECHANACIMIENTO_EDAD_MENOR_MENSAJE = "El usuario debe ser mayor de edad.";
    public static final String USUARIO_NO_ENCONTRADO = "Usuario no encontrado";
    public static final String AUTENTICACION_FALLIDA = "Token de autenticación inválido";
    public static final String FECHANACIMIENTO_NULL_MENSAJE = "La fecha de nacimiento no puede ser nula.";
    public static final String CORREO_INVALIDO_MENSAJE = "Correo inválido.";
    public static final String ROL_INVALIDO_MENSAJE = "Rol inválido. Escribe uno de estos roles: 'ROLE_ADMIN', 'ROLE_AUX_BODEGA', 'ROLE_CLIENT'.";
    public static final String CORREO_REGISTRADO_MENSAJE = "El correo electrónico ya está registrado.";
    public static final String CELULAR_INVALIDO_MENSAJE = "Número de celular inválido";
    public static final String DOCUMENTO_INVALIDO_MENSAJE = "Documento de identidad inválido";
    public static final String CORREO_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String CELULAR_REGEX = "\\+\\d{10,13}";
    public static final String DOCUMENTO_REGEX = "\\d{10}";

    public static final int MIN_EDAD = 18;
}
