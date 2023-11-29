package com.prosantosgui.techunter.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();

        contact.setEmail("prfsantosgui546@gmail.com.br");
        contact.setName("Guilherme dos Santos Maranhão");
        contact.setUrl("https://www.linkedin.com/in/guilherme-dos-santoss/");

        Info info = new Info()
                .title("Job positions portal")
                .version("1.0")
                .description("REST API of a job positions portal")
                .contact(contact);

        return new OpenAPI().components(new Components()
                .addSecuritySchemes("Bearer authentication", createAPIKeyScheme()))
                .addSecurityItem(new SecurityRequirement().addList("Bearer authentication"))
                .info(info);
    }

    private SecurityScheme createAPIKeyScheme(){
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
