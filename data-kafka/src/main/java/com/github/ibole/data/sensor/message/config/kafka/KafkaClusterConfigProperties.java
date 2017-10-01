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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
@ConfigurationProperties(prefix = "kafka.cluster", ignoreInvalidFields = true)
public class KafkaClusterConfigProperties {
	/**
	 * kafka集群地址
	 */
	private String address;

	private KafkaConsumerConfigProperties consumer;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public KafkaConsumerConfigProperties getConsumer() {
		return consumer;
	}

	public void setConsumer(KafkaConsumerConfigProperties consumer) {
		this.consumer = consumer;
	}

  @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
