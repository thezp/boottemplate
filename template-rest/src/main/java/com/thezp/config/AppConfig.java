package com.thezp.config;

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

/**
 * Created by zhangpeng on 2018/1/5.
 */
@Configuration
@EnableSwagger2
public class AppConfig {

    @Configuration
    static class Swagger2Configuration {

        @Bean
        public Docket appRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.thezp.controller"))
                .paths(PathSelectors.any()).build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("模板项目API").description("模板项目接口文档")
                .contact(new Contact("yondaime", "", "111@qq.com"))
                .version("1.0.0").build();
        }
    }

}
