package com.bootcamp.emazon.transaccion_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransaccionMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransaccionMicroApplication.class, args);
	}

}
