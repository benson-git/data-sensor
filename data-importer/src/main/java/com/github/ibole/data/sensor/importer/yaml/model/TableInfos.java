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
package com.github.ibole.data.sensor.importer.yaml.model;

import com.github.ibole.data.sensor.common.handler.TableInfo;

import com.google.common.base.MoreObjects;

import java.util.Collections;
import java.util.List;

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

  private List<TableInfo> global = Collections.emptyList();
  private List<TableInfo> individual = Collections.emptyList();
  /**
   * @return the global
   */
  public List<TableInfo> getGlobal() {
    return global;
  }
  /**
   * @param global the global to set
   */
  public void setGlobal(List<TableInfo> global) {
    this.global = global;
  }
  /**
   * @return the individual
   */
  public List<TableInfo> getIndividual() {
    return individual;
  }
  /**
   * @param individual the individual to set
   */
  public void setIndividual(List<TableInfo> individual) {
    this.individual = individual;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("global", global)
        .add("individual", individual).toString();

  }
}
