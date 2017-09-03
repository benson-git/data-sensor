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
import com.google.common.collect.Lists;

import java.util.List;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

public class DbRow {

  private List<DbColumn> column = Lists.newArrayList();

  /**
   * @return the column
   */
  public List<DbColumn> getColumn() {
    return column;
  }

  /**
   * @param column the column to set
   */
  public void setColumn(List<DbColumn> column) {
    this.column = column;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("column", column).toString();

  }
}
