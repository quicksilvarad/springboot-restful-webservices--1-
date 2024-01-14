package com.quicksilvarad.springbootrestfulwebservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title="Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation" ,
				version ="v1.0",
				contact=@Contact(
						name = "Patrick",
						email = "patrick@gmail.com",
						url="https://www.patrickstar.com"
				),
				license = @License(
						name="Apache 2.0",
						url="https://www.patrickstar.com/license"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Lorem ipsum dolor",
				url="https://www.patrickstar.com/external"
		)
)
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
