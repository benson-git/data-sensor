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
package com.github.ibole.data.sensor.common.model.canal;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/
/**
 * Handler the data changes from dml, ignore the ddl.
 * 
 * @author bwang
 *
 */
public enum EventType {
  /**
   * <code>INSERT = 1;</code>
   */
  INSERT(1),
  /**
   * <code>UPDATE = 2;</code>
   */
  UPDATE(2),
  /**
   * <code>DELETE = 3;</code>
   */
  DELETE(3),  
  /**
   * <code>TRUNCATE = 4;</code>
   */
  TRUNCATE(4),
  
  /**
   * <code>UNKOWN = 5;</code>
   */
  UNKOWN(5);
  
  private final int value;

  private EventType(int value){
      this.value = value;
  }

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }
  
}
