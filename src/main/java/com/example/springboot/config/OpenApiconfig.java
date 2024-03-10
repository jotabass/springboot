package com.example.springboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiconfig {
    @Bean
    public OpenAPI custoOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("ApiProduct")
                        .version("v1")
                        .description("Api para documentação de produtos")
                        .contact(new Contact()
                                .email("jotabelk@gmail.com")
                                .name("Joao Batista"))
                        .license(new License()
                                .name("Spring 3.0")
                                .url("http://localhost:8081/swagger-ui/index.html")));
    }

}
