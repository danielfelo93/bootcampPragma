package com.bootcamp.emazon.transaccion_micro.driving.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-micro", url = "${user.service.url}")
public interface UserClient {

    @PostMapping("/validate-token")
    ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String token);
}
