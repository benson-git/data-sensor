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
package com.github.ibole.data.sensor.common.handler;

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
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.stream.Collectors;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2017, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * 定义需要被跟踪监控的表的相关元数据信息.
 * 
 * @author bwang
 *
 */
public class TableInfo {

  private String tableName;

  private List<String> columnNames;
  
  private List<String> eventTypes;
  
  private Handler handler;

  /**
   * @return the tableName
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * @param tableName the tableName to set
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  /**
   * @return the columnNames
   */
  public List<String> getColumnNames() {
    return columnNames;
  }

  /**
   * @param columnNames the columnNames to set
   */
  public void setColumnNames(List<String> columnNames) {
    if(columnNames!=null) {
     List<String> result = columnNames.stream()   
        .map(line -> line.toUpperCase()) 
        .collect(Collectors.toList()); 
     this.columnNames = result;
    }
  }
  
  /**
   * @return the handler
   */
  public Handler getHandler() {
    return handler;
  }

  /**
   * @param handler the handler to set
   */
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  /**
   * @return the eventTypes
   */
  public List<String> getEventTypes() {
    return eventTypes;
  }

  /**
   * @param eventTypes the eventTypes to set
   */
  public void setEventTypes(List<String> eventTypes) {
    if(eventTypes!=null) {
      List<String> result = eventTypes.stream()   
         .map(line -> line.toUpperCase()) 
         .collect(Collectors.toList()); 
      this.eventTypes = result;
     }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("tableName", tableName)
        .add("columnNames", columnNames)
        .add("eventTypes", eventTypes)
        .add("handler", handler)
        .toString();

  }

}
