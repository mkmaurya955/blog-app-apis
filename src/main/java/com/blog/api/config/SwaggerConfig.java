package com.blog.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
    @Bean
    public OpenAPI customOpenAPI() {
    	final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(Constants.API_TITLE)
                        .description(Constants.API_DESCRIPTION)
                        .version(Constants.API_VERSION)
                        .contact(new Contact().name(Constants.CONTACT_NAME).email(Constants.CONTACT_EMAIL).url(Constants.CONTACT_WEBSITE_URL))
                        .license(new License().name(Constants.LICENCE_NAME).url(Constants.LICENCE_URL)))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                      .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                          .name(securitySchemeName)
                          .type(SecurityScheme.Type.HTTP)
                          .scheme("bearer")
                          .bearerFormat("JWT")))
                ;
    }

//	private ApiInfo getInfo() {
//		return new ApiInfo("Bloggin API Application by Spring Boot", "description:", "1.0", "terms of service",
//				new Contact("Saurabh Maurya", "demo.dev.in", "tgtsaurabhmaurya45@gmail.com"), "Licence of apis",
//				"Api licence url", Collections.emptyList());
//	}
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
