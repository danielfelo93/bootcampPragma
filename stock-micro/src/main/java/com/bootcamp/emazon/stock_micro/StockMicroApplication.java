package com.bootcamp.emazon.stock_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMicroApplication.class, args);
	}

}
