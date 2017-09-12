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
package com.github.ibole.data.sensor.common.monitor;

import com.github.ibole.data.sensor.common.model.canal.DbTable;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * 数据监控处理器.
 * 
 * @author bwang
 *
 */
public interface Monitor {
  /**
   * 初始化.
   * 
   * @param tableInfo 初始化数据TableInfo.
   */
  void init(TableInfo tableInfo, boolean global);
  
  /**
   * 判断是否匹配该Monitor进行数据处理的前提条件.
   * 
   * @param dbTable
   * @return boolean True if matching, otherwise return false.
   */
  boolean match(DbTable dbTable);
  
  /**
   * 处理匹配的表元数据.
   * @param runtimeData Handler运行时要处理的数据.
   */
  void process(RuntimeData runtimeData);
  
  /**
   * 获取下一个处理器.
   * 
   * @return Monitor 下一个处理器.
   */
  Monitor getNext();
  
  /**
   * 设置下一个处理器.
   * 
   * @param  monitor 下一个处理器.
   */
  void setNext(Monitor monitor);
}
