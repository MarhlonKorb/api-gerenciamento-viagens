package com.api.gereciamento.viagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiGerenciamentoViagensApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGerenciamentoViagensApplication.class, args);
	}

}
