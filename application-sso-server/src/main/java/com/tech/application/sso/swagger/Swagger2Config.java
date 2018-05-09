package com.tech.application.sso.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan("com.application.sso.server.controller")
public class Swagger2Config {

	@Bean
	public Docket createAPI() {

		return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).select()
				.apis(RequestHandlerSelectors.any())
				// 过滤生成链接
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfoBuilder().title("Swagger 集成测试").description("Swagger API").version("1.0").build();

		return apiInfo;
	}

}
