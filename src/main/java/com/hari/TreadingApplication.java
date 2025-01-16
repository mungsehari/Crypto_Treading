package com.hari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreadingApplication.class, args);
	}

}
