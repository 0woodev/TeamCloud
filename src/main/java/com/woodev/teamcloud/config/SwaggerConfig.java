package com.woodev.teamcloud.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="https://twojun-space.tistory.com/201">참고 블로그</a>
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    public Info apiInfo() {
        return new Info()
                .title("TeamCloud")
                .description("TeamCloud API Documentation")
                .version("1.0");
    }
}
