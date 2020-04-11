package net.openu.restapi.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigureation {

  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("net.openu.restapi.api"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음

  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("Spring API Documentation")
        .description("API에 대한 연동 문서입니다")
        .license("dean").licenseUrl("http://openu.net").version("1").build();

  }
}