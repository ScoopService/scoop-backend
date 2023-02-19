package com.tomtom.scoop.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Value("${server-url}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/**")
                .build();
    }


    @Bean
    public OpenAPI springShopOpenAPI() {
        SecurityScheme bearerAuth = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityItem = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", bearerAuth))
                .addSecurityItem(securityItem)
                .info(new Info().title("WorkOut API")
                        .description("Workout API 명세서입니다.")
                        .version("v0.0.1"))
                .servers(new ArrayList<Server>() {{
                    add(new Server().url(serverUrl));
                }});
    }

}