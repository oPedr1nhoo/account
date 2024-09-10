package com.microservice.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Documentação MicroServiço de contas",
				description = "Documentação do microserviço de contas do projeto EazyBank de um curso que estou fazendo para" +
						"aprender novos conceitos e padrões",
				version = "v1",
				contact = @Contact(
						name = "Pedro Antônio",
						email = "pedroaia02@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		))
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
