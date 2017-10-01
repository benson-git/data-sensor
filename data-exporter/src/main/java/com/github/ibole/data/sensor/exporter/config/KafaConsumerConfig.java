
package com.github.ibole.data.sensor.exporter.config;

import com.github.ibole.data.sensor.message.config.kafka.KafkaClusterConfigProperties;
import com.github.ibole.data.sensor.message.config.kafka.KafkaConsumerConfiguration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

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


/**
 * @author bwang
 *
 */
@Configuration
@EnableKafka
@ComponentScan(basePackages = {"com.github.ibole.data.sensor.exporter.service.kafka","com.github.ibole.data.sensor.message.service.kafka"})
@EnableConfigurationProperties(KafkaClusterConfigProperties.class)
public class KafaConsumerConfig extends KafkaConsumerConfiguration {

}
