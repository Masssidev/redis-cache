package com.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RediscacheExApplication{

	public static void main(String[] args) {
		SpringApplication.run(RediscacheExApplication.class, args);
	}
}
