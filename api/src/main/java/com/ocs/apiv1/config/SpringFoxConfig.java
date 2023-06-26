package com.ocs.apiv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("192.168.1.30:8080") // Base URL'yi burada belirtin
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ocs.apiv1"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfoMetaData())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder()
                .title("Ocs Repo")
                .description("API Endpoint Decoration")
                .contact(new Contact("Eldem Cihat", "https://www.dev-team.com/", "eldemcihat@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
