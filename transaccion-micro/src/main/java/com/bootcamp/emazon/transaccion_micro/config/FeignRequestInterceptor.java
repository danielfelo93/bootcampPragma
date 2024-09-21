package com.bootcamp.emazon.transaccion_micro.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = RequestSecurityContext.getToken();
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
