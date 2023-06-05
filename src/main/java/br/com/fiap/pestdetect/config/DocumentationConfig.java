package br.com.fiap.pestdetect.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {
    @Bean
    public OpenAPI costumOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                        .title("PestDetect Open API")
                        .version("V1")
                        .description("API para ajudar no controle de pestes em plantações")
                        .contact(new Contact().name("Mateus Marchetti Vieira").email("rm94075@fiap.com.br"))
                    )
                    .components(new Components()
                                    .addSecuritySchemes("bearer-key",
                                                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
