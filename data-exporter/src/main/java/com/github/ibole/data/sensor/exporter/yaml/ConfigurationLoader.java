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
package com.github.ibole.data.sensor.exporter.yaml;

import com.github.ibole.data.sensor.common.handler.BasicHandler;
import com.github.ibole.data.sensor.common.handler.Handler;
import com.github.ibole.data.sensor.common.model.yaml.ColumnRenamingRule;
import com.github.ibole.data.sensor.common.model.yaml.TableInfo;
import com.github.ibole.data.sensor.common.model.yaml.TableInfos;
import com.github.ibole.data.sensor.common.util.ClassHelper;
import com.github.ibole.data.sensor.exporter.pipeline.Pipeline;
import com.github.ibole.data.sensor.exporter.pipeline.StandardPipeline;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.List;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * YAML配置文件加载.
 * 
 * @author bwang
 *
 */
public final class ConfigurationLoader {

	private static final Logger logger = LoggerFactory
			.getLogger(ConfigurationLoader.class);

	private static final String DATA_SENSOR_YAML = "data-sensor.yaml";

	private static final String MONITORINGS = "monitoring";

	private static final String MAPPER_RULE = "mapperRule";

	private ConfigurationLoader() {
	}

	public static List<Pipeline> loadPipelines() {
		List<Pipeline> pipelines = Lists.newArrayList();
		pipelines.add(loadYamlConfig());
		return pipelines;
	}

	/**
	 * 加载Yaml配置文件，解析成Handler list.
	 */
	private static Pipeline loadYamlConfig() {
		Constructor constructor = new Constructor(TableInfos.class);
		TypeDescription projectDesc = new TypeDescription(TableInfos.class);
		projectDesc.putListPropertyType(MONITORINGS, TableInfo.class);
		projectDesc.putListPropertyType(MAPPER_RULE, ColumnRenamingRule.class);
		// constructor.addTypeDescription(
		// new TypeDescription(ExampleEventHandler.class, "!ExampleEventHandler"));
		constructor.addTypeDescription(projectDesc);
		constructor.getPropertyUtils().setSkipMissingProperties(true);
		Yaml yaml = new Yaml(constructor);
		TableInfos tableInfos = yaml.loadAs(ClassHelper.getClassLoader()
				.getResourceAsStream(DATA_SENSOR_YAML), TableInfos.class);

		Pipeline pipeLine = new StandardPipeline();
		pipeLine.setBasic(new BasicHandler());

		return toHandlers(tableInfos, pipeLine);
	}

	private static Pipeline toHandlers(TableInfos tableInfos, Pipeline pipeLine) {

		List<TableInfo> monitorings = tableInfos.getMonitoring();

		if (monitorings != null) {
			for (TableInfo info : monitorings) {
				Handler handler = null;
				try {
					handler = (Handler) ClassHelper.forName(info.getHandler())
							.newInstance();
					handler.init(info, tableInfos.getMapperRule());
					pipeLine.addHandler(handler);
				} catch (Exception e) {
					logger.error("Error for handler instantiation for '{}'",
							info.getHandler(), e);
				}
			}  
		}
		return pipeLine;
	}

}
