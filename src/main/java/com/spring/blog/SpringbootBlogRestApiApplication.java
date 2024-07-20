package com.spring.blog;

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
        info = @Info(
                title = "Blogging platform",
                description = "Spring boot blogging platform app",
                version = "v1.4",
                contact = @Contact(
                        name = "Mohammed Furkhan",
                        email = "mohammedfurkhan9@gmail.com",
                        url = "https://github.com/mfurkhan"
                ),
                license = @License (
                        name = "Apache 2.0",
                        url = "https://github.com/mfurkhan"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring boot Blogging platform app documentation",
                url = "https://github.com/mfurkhan"
        )
)
public class SpringbootBlogRestApiApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {

        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
    }

}
