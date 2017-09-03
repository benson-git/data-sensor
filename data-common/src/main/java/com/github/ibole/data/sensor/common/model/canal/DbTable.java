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

/**
 * @author bwang
 *
 */
public class DbTable {

	private List<DbRow> rows = Lists.newArrayList();

	private EventType eventType;

	private String tableName;

	/**
	 * Constructor.
	 * 
	 * @param rows
	 *            List
	 * @param eventType
	 *            EventType
	 * @param tableName
	 *            String
	 */
	public DbTable(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Constructor.
	 * 
	 * @param row
	 *            List
	 * @param eventType
	 *            EventType
	 * @param tableName
	 *            String
	 */
	public DbTable(List<DbRow> rows, EventType eventType, String tableName) {
		super();
		this.rows = rows;
		this.eventType = eventType;
		this.tableName = tableName;
	}

	public DbTable withTableName(String tableName) {
		return new DbTable(this.rows, this.eventType, tableName);
	}

	public DbTable withEventType(EventType eventType) {
		return new DbTable(this.rows, eventType, this.tableName);
	}

	public DbTable withCanalEventType(
			com.alibaba.otter.canal.protocol.CanalEntry.EventType eType) {

		if (eType.getNumber() == 1) {
			eventType = EventType.INSERT;
		} else if (eType.getNumber() == 2) {
			eventType = EventType.UPDATE;
		} else if (eType.getNumber() == 3) {
			eventType = EventType.DELETE;
		} else if (eType.getNumber() == 4) {
			eventType = EventType.TRUNCATE;
		} else {
			eventType = EventType.UNKOWN;
		}
		return new DbTable(this.rows, this.eventType, this.tableName);
	}

	public DbTable withRow(List<DbRow> row) {
		return new DbTable(row, this.eventType, this.tableName);
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the rows
	 */
	public List<DbRow> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the row to set
	 */
	public void setRows(List<DbRow> rows) {
		this.rows = rows;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("tableName", tableName)
				.add("eventType", eventType).add("rows", rows).toString();

	}
}
