/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.ibole.data.sensor.exporter.service.kafka;

import org.springframework.stereotype.Component;

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
 * Kafka会为每一个consumer group保留一些metadata信息—当前消费的消息的position，也即offset。
 * 这个offset由consumer控制。正常情况下consumer会在消费完一条消息后线性增加这个offset。
 * 当然，consumer也可将offset设成一个较小的值，重新消费一些消息,offet由consumer控制。
 * 
 * @author bwang
 *
 */
@Component
public class KafkaTopicConsumerService<T> {

//  private static final Logger LOGGER =
//      LoggerFactory.getLogger(KafkaTopicConsumerService.class);
//
//  @Autowired
//  @Qualifier("disMatchingSpecPipeline")
//  private Pipeline disMatchingSpecPipeline;
//
//  @Autowired
//  @Qualifier("deptMatchingHospPipeline")
//  private Pipeline deptMatchingHospPipeline;
//  
//  @KafkaListener(topics = KafkaTopicUtil.THIRD_IN_USER, id = "assmtKafkaTopicConsumer")
//  public void consumerKafkaRecords(@Payload List<ConsumerRecord<String, T>> records) {
//    LOGGER.debug("Receive records count:{}", records.size());
//    for (ConsumerRecord<String, T> record : records) {
//
//      String value = String.valueOf(record.value());
//      DbTable dbTable = JSONUtil.toBean(value, DbTable.class);
//      try {
//    	  disMatchingSpecPipeline.getFirst().handle(dbTable);
//    	  deptMatchingHospPipeline.getFirst().handle(dbTable);
//      } catch (Exception ex) {
//        LOGGER.error("Handler处理器执行出错!", ex);
//      }
//      LOGGER.debug("Receive record offset '{}', value '{}':", record.offset(), value);
//    }
//  }
}
