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
package com.github.ibole.data.sensor.exporter.yaml.model;

import com.github.ibole.data.sensor.common.monitor.Monitor;
import com.github.ibole.data.sensor.common.monitor.TableInfo;

import com.google.common.base.MoreObjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
public class TableInfos {

  private List<TableInfo> monitoring = Collections.emptyList();
  private Map<String, Monitor> registry = new HashMap<>();
  
  /**
   * @return the monitoring
   */
  public List<TableInfo> getMonitoring() {
    return monitoring;
  }
  /**
   * @param monitoring the monitoring to set
   */
  public void setMonitoring(List<TableInfo> global) {
    this.monitoring = global;
  }
  /**
   * @return the registry
   */
  public Map<String, Monitor> getRegistry() {
    return registry;
  }
  /**
   * @param registry the registry to set
   */
  public void setRegistry(Map<String, Monitor> registry) {
    this.registry = registry;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("monitoring", monitoring)
        .add("registry", registry).toString();

  }
}
