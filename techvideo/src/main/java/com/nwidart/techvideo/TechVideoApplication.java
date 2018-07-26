package com.nwidart.techvideo;

import com.sendgrid.SendGrid;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TechVideoApplication {

  @Value("${sendgrid.api.key}")
  private String sendGridAPIKey;

  public static void main(String[] args) {
    SpringApplication.run(TechVideoApplication.class, args);
  }

  @Bean
  public SendGrid sendGridClient() {
    return new SendGrid(sendGridAPIKey);
  }

  @Bean
  public Docket mainConfig() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/api/.*"))
        .build()
        .apiInfo(apiInfo())
        .directModelSubstitute(LocalDate.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Tech Video")
        .description("Manage tech learning sessions")
        .version("1.0")
        .contact(new Contact("Nicolas Widart", "https://nicolaswidart.com", "n.widart@gmail.com"))
        .build();
  }
}
