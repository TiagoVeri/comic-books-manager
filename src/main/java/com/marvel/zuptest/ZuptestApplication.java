package com.marvel.zuptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ZuptestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuptestApplication.class, args);
	}

}
