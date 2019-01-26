package com.jay.integration.springintegrations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	private DirectChannel inputChannel;
	
	@Autowired
	private DirectChannel outputChannel;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, Object> map = Map.of("Key", "Value");
		MessageHeaders headers = new MessageHeaders(map);
		Message<String> message = MessageBuilder.withPayload("Hello from spring integration with MessageBuilder.").copyHeaders(headers).build();
		outputChannel.subscribe(msg -> {
			System.err.println(msg.getPayload());
		});
		inputChannel.send(message);
	}
}

