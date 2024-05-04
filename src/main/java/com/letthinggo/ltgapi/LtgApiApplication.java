package com.letthinggo.ltgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LtgApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtgApiApplication.class, args);
	}

}
