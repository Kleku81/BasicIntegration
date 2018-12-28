package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.in.SiWrapperServiceAnnotated;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(SiApplication.class, args);

    SiWrapperServiceAnnotated wrapperService =
        ctx.getBean(SiWrapperServiceAnnotated.class);
    String text = "simple message";
    log.info("Sending message: " + text);
    boolean result = wrapperService.processText(text);
    log.info("Result: " + result);
  }
}

