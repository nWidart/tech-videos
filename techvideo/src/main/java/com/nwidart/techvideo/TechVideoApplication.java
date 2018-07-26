package com.nwidart.techvideo;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
}
