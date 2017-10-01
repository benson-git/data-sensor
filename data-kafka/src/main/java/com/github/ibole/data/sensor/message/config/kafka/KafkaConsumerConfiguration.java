/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ibole.data.sensor.message.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * @author bwang
 *
 */
@Configuration
@EnableKafka
@ComponentScan(basePackages = "com.github.ibole.data.sensor.message.service.kafka")
@EnableConfigurationProperties(KafkaClusterConfigProperties.class)//开启属性注入,通过@autowired注入  
public class KafkaConsumerConfiguration {

	@Autowired
	private KafkaClusterConfigProperties kafkaConfig;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		// list of host:port pairs used for establishing the initial connections
		// to the Kakfa cluster
		KafkaConsumerConfigProperties consumerConfig = kafkaConfig
				.getConsumer();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				kafkaConfig.getAddress());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
		    StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
		    StringDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
				consumerConfig.isAutoCommitOffset());

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		// allows a pool of processes to divide the work of consuming and
		// processing records
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.getGroupId());

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setPollTimeout(
				kafkaConfig.getConsumer().getPollTimeout());
		factory.setBatchListener(true);
		return factory;
	}
	
	@Bean
	@Scope("prototype")
	public KafkaConsumer<String, String> kafkaConsumer() {
	  return new KafkaConsumer<>(consumerConfigs());
	}

}
