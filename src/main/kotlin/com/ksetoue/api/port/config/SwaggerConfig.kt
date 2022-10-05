package com.ksetoue.api.port.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun api(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("")
            .pathsToMatch("/controller/**")
            .build()
    }

    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title("Spring Sample API")
                    .description("Spring sample API application")
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("This is the documentation of the sample api")
                    .url("http://spring/docs")
            )
    }
}
