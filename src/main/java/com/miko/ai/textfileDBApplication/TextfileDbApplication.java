package com.miko.ai.textfileDBApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TextfileDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextfileDbApplication.class, args);
	}

}
