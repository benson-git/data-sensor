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

import com.google.common.base.MoreObjects;

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
public class DbColumn {

  private boolean key;
  private String name;
  private String value;
  private String mysqlType;
  /**
   * @return the key
   */
  public boolean isKey() {
    return key;
  }
  /**
   * @param key the key to set
   */
  public void setKey(boolean key) {
    this.key = key;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }
  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
  /**
   * @return the mysqlType
   */
  public String getMysqlType() {
    return mysqlType;
  }
  /**
   * @param mysqlType the mysqlType to set
   */
  public void setMysqlType(String mysqlType) {
    this.mysqlType = mysqlType;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("value", value)
        .add("key", key)
        .add("mysqlType", mysqlType).toString();

  }
}
