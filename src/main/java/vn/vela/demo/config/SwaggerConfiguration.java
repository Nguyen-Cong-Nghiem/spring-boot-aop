package vn.vela.demo.config;

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
public class SwaggerConfiguration {

  /**
   * Api docket.
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false);
  }

  private ApiInfo apiInfo() {
    ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
    apiInfoBuilder.title("REST API");
    apiInfoBuilder.description("API for Core Service");
    apiInfoBuilder.version("1.0-SNAPSHOT");
    apiInfoBuilder.contact(new Contact("Velacorp", "https://velacorp.vn", ""));
    return apiInfoBuilder.build();
  }

}
