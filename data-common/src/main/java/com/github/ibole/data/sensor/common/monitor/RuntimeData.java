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

import com.google.common.base.MoreObjects;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * 在管道流处理过程中的运行时数据,会被各个handler处理.
 * 
 * @author bwang
 *
 */
public class RuntimeData {

  private DbTable dbTable;
  //是否在管道流的处理过程中,有global handler处理过DbTable.
  private boolean globalHandler;

  /**
   * @return the dbTable
   */
  public DbTable getDbTable() {
    return dbTable;
  }

  /**
   * @param dbTable the dbTable to set
   */
  public void setDbTable(DbTable dbTable) {
    this.dbTable = dbTable;
  }

  /**
   * @return the globalHandler
   */
  public boolean isGlobalHandler() {
    return globalHandler;
  }

  /**
   * @param globalHandler the globalHandler to set
   */
  public void setGlobalHandler(boolean globalHandler) {
    this.globalHandler = globalHandler;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("dbTable", dbTable)
        .add("globalHandler", globalHandler).toString();

  }
}
