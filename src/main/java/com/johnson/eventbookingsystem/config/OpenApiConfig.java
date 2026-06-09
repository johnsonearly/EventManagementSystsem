package com.johnson.eventbookingsystem.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");
        return new OpenAPI()
                .info(new Info()
                        .title("Event Management API")
                        .version("1.0")
                        .description("API documentation for managing system events"));
    }
}
