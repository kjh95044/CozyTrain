package com.ssafy.cozytrain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
        Server devServer = new Server("dev", "https://dev.cozytrain.com", "for dev server", Collections.emptyList(), Collections.emptyList());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .servers(serverLocal,devServer)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.cozytrain"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("CozyTrain API")
                .version("1.0")
                .build();
    }

}
