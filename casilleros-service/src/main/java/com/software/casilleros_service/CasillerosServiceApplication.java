package com.software.casilleros_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.software.casilleros_service")
public class CasillerosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasillerosServiceApplication.class, args);
	}

}
