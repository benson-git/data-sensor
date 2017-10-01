package com.github.ibole.data.sensor.exporter;

import com.github.ibole.data.sensor.exporter.config.KafaConsumerConfig;
import com.github.ibole.infrastructure.common.spring.SpringContextHolder;

import org.springframework.beans.factory.annotation.Qualifier;
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
@Import({KafaConsumerConfig.class})
@EnableAutoConfiguration
public class ExporterDeployApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExporterDeployApplication.class, args);
	}

	@Bean
	@Qualifier("springContextHolder")
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}
}
