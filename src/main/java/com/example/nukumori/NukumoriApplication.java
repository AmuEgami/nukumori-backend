package com.example.nukumori;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.nukumori")
public class NukumoriApplication {

	public static void main(String[] args) {
		SpringApplication.run(NukumoriApplication.class, args);
	}

}
