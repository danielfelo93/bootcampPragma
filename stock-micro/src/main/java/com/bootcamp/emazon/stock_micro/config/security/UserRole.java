package com.bootcamp.emazonapi.config.security;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    AUX_BODEGA("ROLE_AUX_BODEGA"),
    CLIENT("ROLE_CLIENT"),
    USER("ROLE_USER");

    private final String rol;

    UserRole(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}