package com.github.ibole.data.sensor.importer;

import com.github.ibole.data.sensor.common.model.canal.DbTable;
import com.github.ibole.data.sensor.message.service.kafka.KafkaPublishService;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

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

@Component
public class CanalClient extends AbstractCanalClient {

	@Autowired
	private KafkaPublishService<DbTable> publishService;

	@Value("${kafka.topic}")
	private String topic;

	public void boot(String ip, int port, String dest) {
		// 根据ip，直接创建链接，无HA的功能
		CanalConnector connector = CanalConnectors.newSingleConnector(
				new InetSocketAddress(ip, port), dest, "", "");

		setDestination(destination);
		setConnector(connector);
		launch();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					logger.info("## stop the canal client");
					shutdown();
				} catch (Exception e) {
					logger.warn(
							"##something goes wrong when stopping canal:\n{}",
							ExceptionUtils.getFullStackTrace(e));
				} finally {
					logger.info("## canal client is down.");
				}
			}

		});
	}

	@Override
	public void sendMessage(DbTable dbTable) {
		publishService.publish(topic, dbTable);

	}
}
