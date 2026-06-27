package com.ecommerce.yep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YepApplication {

	public static void main(String[] args) {
		SpringApplication.run(YepApplication.class, args);
	}

}
