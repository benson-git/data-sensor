package com.github.ibole.data.sensor.message.service.kafka.impl;
///*
// * Copyright 2016-2017 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package top.oklife.hcloud.common.service.kafka.impl;
//
//import top.oklife.hcloud.common.spring.config.kafka.KafkaTopicUtil;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///*********************************************************************************************.
// * 
// * 
// * <p>Copyright 2016, iBole Inc. All rights reserved.
// * 
// * <p></p>
// *********************************************************************************************/
//
///**
// * @author bwang
// *
// */
//@Component
//public class UserKafkaTopicConsumerService<T> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserKafkaTopicConsumerService.class);
//
//    @KafkaListener(topics = KafkaTopicUtil.THIRD_IN_USER, id = "userKafkaTopicConsumer")
//    public void consumerKafkaRecords(@Payload  List<ConsumerRecord<String, T>> records) {
//       LOGGER.debug("Receive records count:{}", records.size());
//    }
//
//}
