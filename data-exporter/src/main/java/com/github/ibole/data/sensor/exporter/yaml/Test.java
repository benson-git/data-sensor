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

import com.github.ibole.data.sensor.common.handler.RuntimeData;
import com.github.ibole.data.sensor.common.model.canal.DbColumn;
import com.github.ibole.data.sensor.common.model.canal.DbRow;
import com.github.ibole.data.sensor.common.model.canal.DbTable;
import com.github.ibole.data.sensor.common.model.canal.EventType;
import com.github.ibole.data.sensor.exporter.pipeline.Pipeline;

import com.google.common.collect.Lists;

import java.util.List;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2017, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * @author bwang
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DbTable dbTable = new DbTable("AMNT_MatchingRule");
		dbTable.setEventType(EventType.UPDATE);
		List<DbRow> rows = Lists.newArrayList();
		DbRow row1 = new DbRow();
		DbRow row2 = new DbRow();
		
		List<DbColumn> columns1 = Lists.newArrayList();
		
		DbColumn column11 = new DbColumn();
		column11.setName("id");
		column11.setValue("2222");
		column11.setMysqlType("bigint");
		
		DbColumn column12 = new DbColumn();
		column12.setName("userName");
		column12.setMysqlType("varchar");
		column12.setValue("bwang");
		columns1.add(column11);
		columns1.add(column12);
		
		
		List<DbColumn> columns2 = Lists.newArrayList();
		
		DbColumn column21 = new DbColumn();
		column21.setName("id");
		column21.setValue("2223");
		column21.setMysqlType("bigint");
		
		DbColumn column22 = new DbColumn();
		column22.setName("userName");
		column22.setMysqlType("varchar");
		column22.setValue("bwang1");
		columns2.add(column21);
		columns2.add(column22);
		
		row1.setColumn(columns1);
		row2.setColumn(columns2);
		
		rows.add(row1);
		rows.add(row2);
		dbTable.setRows(rows);
		
		RuntimeData runtimeData = new RuntimeData();
		runtimeData.setDbTable(dbTable);
		
		List<Pipeline> pipelines = ConfigurationLoader.loadPipelines();
		pipelines.get(0).getFirst().process(runtimeData);
	}

}
