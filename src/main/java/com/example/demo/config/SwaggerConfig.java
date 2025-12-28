package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    // ✅ Method EXPECTED by test cases
    @Bean
    public OpenAPI api() {
        return customOpenAPI();
    }

    // ✅ Your existing implementation (UNCHANGED)
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                // Server URL
                .servers(List.of(
                        new Server().url("https://9305.408procr.amypo.ai")
                ))

                // JWT Security Scheme
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )

                // Apply security globally
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }
}
