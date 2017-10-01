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

package com.github.ibole.data.sensor.exporter;

import com.github.ibole.data.sensor.common.handler.RuntimeData;
import com.github.ibole.data.sensor.common.model.canal.DbTable;
import com.github.ibole.data.sensor.common.util.JsonUtil;
import com.github.ibole.data.sensor.exporter.pipeline.Pipeline;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
@Component("consumerTask")
@Scope("prototype")
public class ConsumerThread extends Thread {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerThread.class);
  private static final int RETRY_NUM = 3;
  private final AtomicBoolean closed = new AtomicBoolean(false);
  // KafkaConsumer kafka消费者
  @Autowired
  private KafkaConsumer<String, String> kafkaConsumer;

  // 消费的topic组
  private List<String> topics;

  private List<Pipeline> pipelines;

  public void setTopic(String topic) {
    this.topics = Arrays.asList(topic);
  }

  public void setPipelines(List<Pipeline> pipelines) {
    this.pipelines = pipelines;
  }

  @Override
  public void run() {
    RuntimeData runtimeData;
    kafkaConsumer.subscribe(topics);
    while (!closed.get()) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
      int count = 0;
      boolean success = false;
      
      try {
        Thread.sleep(1000);
      } catch (InterruptedException nte) {   
        LOGGER.error("{} Interrupted", this.getName(),  nte);
        // clean up state...
        Thread.currentThread().interrupt();
      }
      
      do {
        try {
          ++count;
          for (ConsumerRecord<String, String> record : records) {
            String value = String.valueOf(record.value());
            LOGGER.info("消费者的名字为: {} , 消费的消息为：{} ", this.getName(), value);
            DbTable dbTable = JsonUtil.toBean(value, DbTable.class);
            for (Pipeline pipeline : pipelines) {
              runtimeData = new RuntimeData();
              runtimeData.setDbTable(dbTable);
              pipeline.getFirst().process(runtimeData);
            }
            // 手动提交
            kafkaConsumer.commitSync();
            LOGGER.info("消费者名字为 '{}' 的消费已经处理完成，提交完毕", this.getName());
          }
          success = true;
        } catch (WakeupException e) {
          // Ignore exception if closing
          if (!closed.get())
            throw e;
        } catch (Exception ex) {
          LOGGER.error("消费者名字为 '{}' 的消费处理失败!", this.getName(), ex);
          success = false;
        }
      } while (!success && count < RETRY_NUM);
    }
  }

  // Shutdown hook which can be called from a separate thread
  public void shutdown() {
    closed.set(true);
    kafkaConsumer.wakeup();
  }
}
