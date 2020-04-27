package com.edureka.ms.training.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//xml
@Configuration
public class AppConfig {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
      return new RestTemplate();
  }
}