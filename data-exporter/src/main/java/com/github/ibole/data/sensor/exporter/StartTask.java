package com.github.ibole.data.sensor.exporter;

import com.github.ibole.data.sensor.exporter.pipeline.Pipeline;
import com.github.ibole.data.sensor.exporter.yaml.ConfigurationLoader;
import com.github.ibole.infrastructure.common.spring.SpringContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*********************************************************************************************
 * .
 * 
 * 
 * <p>
 * 版权, 2017, 深圳生命量医疗科技有限公司. 保留所有权利.
 * 
 * <p>
 * </p>
 *********************************************************************************************/

@Component
public class StartTask {

	private static final Logger logger = LoggerFactory
			.getLogger(StartTask.class);
	private ConsumerThread moniotrTask;

	@Value("${consumer.taskname}")
	private String taskName;

	@Value("${kafka.topic}")
	private String topic;

	// 定义在构造方法完毕后，执行这个初始化方法
	@PostConstruct
	public void init() {

		final List<Pipeline> list = ConfigurationLoader.loadPipelines();

		logger.info("监控任务的总Pipeline数：{}", list.size());

		moniotrTask = SpringContextHolder.getBean("consumerTask",
				ConsumerThread.class);
		moniotrTask.setName(taskName);
		moniotrTask.setTopic(topic);
		moniotrTask.setPipelines(list);
		moniotrTask.start();

	}

	@PreDestroy
	public void destroy() {
		moniotrTask.shutdown();
		logger.info("关闭监控任务线程：{}", moniotrTask.getName());
	}
}
