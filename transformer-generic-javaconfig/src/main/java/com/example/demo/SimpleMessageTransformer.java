package com.example.demo;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

@MessageEndpoint
public class SimpleMessageTransformer {
  @Transformer(inputChannel = "inChannel", outputChannel = "transformedChannel")
  public String transformMessage(Message message) {
	  
	  System.out.println("headres" + message.getHeaders().toString());
	  
    return new String(message.getPayload() + " transformede");
  }
}