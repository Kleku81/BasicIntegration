package com.example.demo.in;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SiWrapperServiceAnnotated {
  @Gateway(requestChannel = "inChannel")
  public boolean processText(String text);
}