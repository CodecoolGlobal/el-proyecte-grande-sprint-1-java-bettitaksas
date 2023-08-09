package com.fridgemaster.demo;

import com.fridgemaster.demo.model.FridgeRepository;
import com.fridgemaster.demo.service.FridgeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FridgemasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FridgemasterApplication.class, args);
	}
}
