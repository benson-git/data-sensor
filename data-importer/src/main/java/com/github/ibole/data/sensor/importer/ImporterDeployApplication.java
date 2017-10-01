package com.github.ibole.data.sensor.importer;

import com.github.ibole.data.sensor.message.config.kafka.KafkaProducerConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/*********************************************************************************************
 * .
 * 
 * 
 * <p>
 * Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p>
 * </p>
 *********************************************************************************************/

@SpringBootApplication
@Import({KafkaProducerConfiguration.class})
@EnableAutoConfiguration
public class ImporterDeployApplication {

	@Value("${canal.listener.enalbe}")
	private Boolean enable;

	@Value("${canal.server.ip}")
	private String ip;

	@Value("${canal.server.port}")
	private int port;

	@Value("${canal.server.destination}")
	private String dest;

	public static void main(String[] args) {

		SpringApplication.run(ImporterDeployApplication.class, args);
	}

//	@Bean
//	@Qualifier("springContextHolder")
//	public SpringContextHolder springContextHolder() {
//		return new SpringContextHolder();
//	}

	@Bean
	//@DependsOn(value="springContextHolder")
	public CanalClient canalClient() {
		CanalClient client = new CanalClient();
		// launch the Cannel listener
		if (enable != null && enable) {
			client.boot(ip, port, dest);
		}
		return client;
	}
}
