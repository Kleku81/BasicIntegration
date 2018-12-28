package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
//@IntegrationComponentScan
public class SiConfiguration {
  @Bean
  public MessageChannel inChannel() {
    return new DirectChannel();
  }

  @Bean
  public MessageChannel transformedChannel() {
    return new DirectChannel();
  }
}