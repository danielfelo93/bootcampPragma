package com.bootcamp.emazon.transaccion_micro.config;

public class RequestSecurityContext {

    private static final ThreadLocal<String> currentToken = new ThreadLocal<>();

    public static void setToken(String token) {
        currentToken.set(token);
    }

    public static String getToken() {
        return currentToken.get();
    }

    public static void clear() {
        currentToken.remove();
    }

    private RequestSecurityContext() {
    }
}
