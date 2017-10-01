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

package com.github.ibole.data.sensor.message.service.kafka.impl;

import com.github.ibole.data.sensor.common.util.JsonUtil;
import com.github.ibole.data.sensor.message.service.kafka.KafkaPublishService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * @author bwang
 * @param <T>
 *
 */
@Service
public class KafkaPublishServiceImpl<T> implements KafkaPublishService<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublishServiceImpl.class);
    private static final Integer TWO_SECOND = 2;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String topic, T data) {
        publishJsonString(topic, data);
    }

    private void publishJsonString(String topic, T data) {
        try{
            LOGGER.info("Send data {} to kafka topic {}.", data, topic);
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, JsonUtil.toJson(data));
            send.get(TWO_SECOND, TimeUnit.SECONDS);
        }catch (Exception e) {
            LOGGER.error("publish message failed", e);
        }
    }
}