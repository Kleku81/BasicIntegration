package com.example.demo;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig{
    public String INPUT_DIR = "/root/demo_in";
    public String OUTPUT_DIR = "/root/demo_out";
    public String FILE_PATTERN = "*.mpeg";
 
    @Bean
    public MessageChannel inChannel() {
        return new DirectChannel();
    }
    
    @Bean
    public MessageChannel transformedChannel() {
        return new DirectChannel();
    }
 
    @Bean
    @InboundChannelAdapter(value = "inChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader= new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }
 
    @Bean
    @ServiceActivator(inputChannel= "transformedChannel")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }
    
    
    @MessageEndpoint
    public class SimpleMessageTransformer {
      @Transformer(inputChannel = "inChannel", outputChannel = "transformedChannel")
      public File transformMessage(Message<File> input) {
    	  
    	  File filePayload = input.getPayload();
    	  System.out.println("File name = "+ filePayload.getName()+", size = "+ filePayload.length()+ ", path = "+ filePayload.getAbsolutePath());
    	  
        return filePayload;
      }
    }
}