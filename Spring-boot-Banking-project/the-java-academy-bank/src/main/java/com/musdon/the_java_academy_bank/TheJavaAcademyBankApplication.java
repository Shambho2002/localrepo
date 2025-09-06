package com.musdon.the_java_academy_bank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "The Java Tutorials Bank App",
				description = "Backend Rest APIs for TJA Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Shambhu Sharma",
						email = "sharshambho@gmail.com",
						url = "https://github.com/Shambho2002/tja_bank_app"
				),
				license = @License(
						name = "The Java Tutorials",
						url = "https://github.com/Shambho2002/tja_bank_app"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "The Java Tutorials Bank App Documentation",
				url = "https://github.com/Shambho2002/tja_bank_app"
		)
)
public class TheJavaAcademyBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheJavaAcademyBankApplication.class, args);
	}

}
