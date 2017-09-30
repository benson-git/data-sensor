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
package com.github.ibole.data.sensor.common.model.yaml;

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

	private List<TableInfo> monitoring = Collections.emptyList();
	private List<ColumnRenamingRule> mapperRule = Collections.emptyList();

	/**
	 * @return the monitoring
	 */
	public List<TableInfo> getMonitoring() {
		return monitoring;
	}

	/**
	 * @param monitoring
	 *            the monitoring to set
	 */
	public void setMonitoring(List<TableInfo> global) {
		this.monitoring = global;
	}


	/**
	 * @return the mapperRule
	 */
	public List<ColumnRenamingRule> getMapperRule() {
		return mapperRule;
	}

	/**
	 * @param mapperRule the mapperRule to set
	 */
	public void setMapperRule(List<ColumnRenamingRule> mapperRule) {
		this.mapperRule = mapperRule;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("monitoring", monitoring)
				.add("mapperRule", mapperRule).toString();

	}
}
