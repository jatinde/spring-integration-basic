package com.jay.integration.springintegrations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class PrintService {
	private static final Logger LOG = LoggerFactory.getLogger(PrintService.class);
	
	public Message<?> print(Message<String> message) {
		MessageHeaders headers = message.getHeaders();
		headers.forEach((key, value) -> {
			LOG.info(key + " -----> " + value);
		});
		LOG.info(message.getPayload());
		return MessageBuilder.withPayload("Message Send to output channel By MessageBuilder").build();
	}
}
