package com.luan.authenticationservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title = "Exam API",
		description = "Doing CRUD Operation",
		summary = "Restful api demo for hr management system",
		termsOfService = "T&C",
		contact = @Contact(
			name = "Luan NT",
			email = "nguyentranluanit@gmail.com"
		),
		license = @License(
			name = "Your License No"
		),
		version = "v1"
	),
	servers = {
		@Server(
			description = "Dev",
			url = "http://localhost:8080"
		),
		@Server(
			description = "Test",
			url = "http://localhost:8080"
		),
	},
	security = {
		@SecurityRequirement(
			name = "bearerAuth"
		)
	}
)
@SecurityScheme(
	name = "bearerAuth",
	description = "JWT auth description",
	scheme = "bearer",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
