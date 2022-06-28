package com.classpath.orderrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Orders API - V1", contact = @Contact(email = "support@bla.com"))
		)
public class OrderRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderRestApiApplication.class, args);
	}

}
