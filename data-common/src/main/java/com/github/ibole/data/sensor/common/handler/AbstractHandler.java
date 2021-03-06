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

import com.github.ibole.data.sensor.common.model.canal.DbColumn;
import com.github.ibole.data.sensor.common.model.canal.DbRow;
import com.github.ibole.data.sensor.common.model.canal.DbTable;
import com.github.ibole.data.sensor.common.model.canal.EventType;
import com.github.ibole.data.sensor.common.model.yaml.ColumnRenamingRule;
import com.github.ibole.data.sensor.common.model.yaml.TableInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class AbstractHandler implements Handler {

	public static final String UPDATE_EVENT = "update";

	protected Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());

	protected TableInfo tableInfo;
	
	protected List<ColumnRenamingRule> mapperRule;

	protected Handler nextHandler;

	/**
	 * 初始化.
	 * 
	 * @param tableInfo
	 *            初始化数据TableInfo.
     * @param mapperRule 
     *            列名重命名规则.
	 */
	@Override
	public void init(TableInfo tableInfo, List<ColumnRenamingRule> mapperRule) {
		this.tableInfo = tableInfo;
        this.mapperRule = mapperRule;
        registerProcessor();
	}

	protected TableInfo getTableInfo() {
		return tableInfo;
	}

	/**
	 * @return the mapperRule
	 */
	protected List<ColumnRenamingRule> getMapperRule() {
		return mapperRule;
	}

	/**
	 * 获取下一个处理器.
	 * 
	 * @return Handler 下一个处理器.
	 */
	@Override
	public Handler getNext() {
		return this.nextHandler;
	}

	/**
	 * 设置下一个处理器.
	 * 
	 * @param monitor
	 *            下一个处理器.
	 */
	@Override
	public void setNext(Handler monitor) {
		this.nextHandler = monitor;
	}

	/**
	 * 处理具体的匹配计算.
	 * 
	 * @param dbTable
	 *            运行时Handler要处理的数据.
	 */
	public abstract void doProcess(RuntimeData runtimeData);
	
	/**
	 * 注册处理器.
	 */
	public abstract void registerProcessor();

	/**
	 * 处理匹配计算.
	 * 
	 * @param dbTable
	 *            运行时Handler要处理的数据.
	 */
	public void process(RuntimeData runtimeData) {

		if (filter(runtimeData.getDbTable())) {
			runtimeData.setMapperRule(getMapperRule());
			doProcess(runtimeData);
		} else {
			logger.info("Skip to handle '{}'", runtimeData.getDbTable()
					.getTableName());
		}

		if (getNext() != null) {
			getNext().process(runtimeData);
		}

	}

	@Override
	public boolean filter(DbTable dbTable) {

		boolean matchedFlag = false;

		if (getTableInfo().getTableName().equalsIgnoreCase(
				dbTable.getTableName())) {
			// 配置中，不明确指定事件，表示监听所有事件
			if (getTableInfo().getEventTypes() == null
					|| getTableInfo().getEventTypes().isEmpty()) {
				return true;
			}

			EventType eventType = dbTable.getEventType();
			// 是否是update事件
			if (UPDATE_EVENT.equalsIgnoreCase(eventType.name())) {
				// 配置中，不明确指定列名，表示监听所有列
				if (getTableInfo().getColumnNames() == null
						|| getTableInfo().getColumnNames().isEmpty()
						// 判断接收的dbTable数据
						|| matchColumn(dbTable)) {
					return true;
				}

			} else if (getTableInfo().getEventTypes()
					.contains(eventType.name())) {
				return true;
			}
		}
		return matchedFlag;

	}

	protected boolean matchColumn(DbTable dbTable) {
		List<DbRow> rows = dbTable.getRows();
		for (DbRow row : rows) {
			List<DbColumn> columns = row.getColumn();
			for (DbColumn column : columns) {
				if (getTableInfo().getColumnNames().contains(
						column.getName().toUpperCase())) {
					return true;
				}
			}
		}
		return false;
	}

}
