package com.r6overwatch.overwatchapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuration for swagger
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.r6overwatch.overwatchapi.controllers.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Overwatch API",
                "Overwatch API is a data tracking api primarily focused on helping individuals and squads track their performances game to game in the hopes " +
                        "of reaching that max rank and achieving greatness.",
                "Overwatch API 1.0",
                "Terms of service",
                new Contact("Stephen Prizio", "www.saprizio.com", "s.prizio@hotmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
