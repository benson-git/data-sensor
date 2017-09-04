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
import com.github.ibole.data.sensor.common.handler.TableInfo;
import com.github.ibole.data.sensor.exporter.handler.ExampleHandler;
import com.github.ibole.data.sensor.exporter.pipeline.Pipeline;
import com.github.ibole.data.sensor.exporter.pipeline.StandardPipeline;
import com.github.ibole.data.sensor.exporter.yaml.model.TableInfos;
import com.github.ibole.infrastructure.common.utils.ClassHelper;

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

  private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

  private static final String DATA_SENSOR_YAML = "data-sensor.yaml";
  
  private static final String GLOBAL = "global";
  
  private static final String INDIVIDUAL = "individual";

  private ConfigurationLoader() {}
  
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
    projectDesc.putListPropertyType(GLOBAL, TableInfo.class);
    projectDesc.putListPropertyType(INDIVIDUAL, TableInfo.class);
    constructor.addTypeDescription(
        new TypeDescription(ExampleHandler.class, "!ExampleHandler"));
    constructor.addTypeDescription(projectDesc);
    constructor.getPropertyUtils().setSkipMissingProperties(true);
    Yaml yaml = new Yaml(constructor);
    TableInfos tableMonitoring = yaml.loadAs(
    		ClassHelper.getClassLoader().getResourceAsStream(DATA_SENSOR_YAML), TableInfos.class);

    Pipeline pipeLine = new StandardPipeline();
    pipeLine.setBasic(new BasicHandler());

    return toHandlers(tableMonitoring, pipeLine);
  }

  private static Pipeline toHandlers(TableInfos tableMonitoring, Pipeline pipeLine) {

    List<TableInfo> gTableInfos = tableMonitoring.getGlobal();
    List<TableInfo> iTableInfos = tableMonitoring.getIndividual();
    
    if (gTableInfos != null) {
      for (TableInfo info : gTableInfos) {
        Handler handler = info.getHandler();
        if (handler != null) {
          handler.init(info, true);
          pipeLine.addHandler(handler);
        } else {
          logger.warn("No hanlder defined or skip the handler for '{}'", info.getTableName());
        }
      }
    }
    if (iTableInfos != null) {
      for (TableInfo info : iTableInfos) {
        Handler handler = info.getHandler();
        if (handler != null) {
          handler.init(info, false);
          pipeLine.addHandler(handler);
        } else {
          logger.warn("No hanlder defined for '{}'", info.getTableName());
        }
      }
    }
    return pipeLine;
  }

}
