package com.bootcamp.emazon.transaccion_micro.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;


@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor feigRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}