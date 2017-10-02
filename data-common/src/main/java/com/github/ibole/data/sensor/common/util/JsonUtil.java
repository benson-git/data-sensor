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

package com.github.ibole.data.sensor.common.util;

import com.github.ibole.data.sensor.common.model.canal.DbColumn;
import com.github.ibole.data.sensor.common.model.canal.DbRow;
import com.github.ibole.data.sensor.common.model.canal.DbTable;
import com.github.ibole.data.sensor.common.model.canal.EventType;
import com.github.ibole.data.sensor.common.model.yaml.ColumnRenamingRule;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public final class JsonUtil {

	private static Gson gson = null;

	static {
		gson = new Gson();// todo yyyy-MM-dd HH:mm:ss
	}

	public static String string2Json(String s) {
		if (Strings.isNullOrEmpty(s)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toJson(DbTable dbTable,
			List<ColumnRenamingRule> mapperRule) {
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("[");
		List<DbRow> rows = dbTable.getRows();
		for (DbRow row : rows) {
			jsonStr.append("{");
			for (DbColumn column : row.getColumn()) {
				String calculatedColumnName = applyColumnRenamingRule(
						column.getName(), mapperRule);
				calculatedColumnName = BeanUtil.getCamelCaseString(
						calculatedColumnName, false);
				jsonStr.append("\"").append(calculatedColumnName).append("\"")
						.append(":");
				if (isStringValue(column.getMysqlType())) {
					jsonStr.append("\"").append(string2Json(column.getValue()))
							.append("\"");
				} else {
					jsonStr.append(string2Json(column.getValue()));
				}
				jsonStr.append(",");
			}
			jsonStr.setCharAt(jsonStr.length() - 1, '}');
			jsonStr.append(",");
		}
		jsonStr.setCharAt(jsonStr.length() - 1, ']');
		return jsonStr.toString();
	}

	private static boolean isStringValue(String mySqlType) {
		boolean isString = true;
		String pureMysqlType = mySqlType;
		//mysqlType=varchar(100)
		int index = mySqlType.indexOf('(');
		if(index > 0) {
		   pureMysqlType = mySqlType.substring(0, index);
		}
		switch (pureMysqlType.toUpperCase()) {
		case "BIT":
			isString = false;
			break;
		case "TINYINT":
			isString = false;
			break;
		case "SMALLINT":
			isString = false;
			break;
		case "INTEGER":
			isString = false;
			break;
		case "BIGINT":
			isString = false;
			break;
		case "FLOAT":
			isString = false;
			break;
		case "DOUBLE":
			isString = false;
			break;
		case "NUMERIC":
			isString = false;
			break;
		case "DECIMAL":
			isString = false;
			break;
		}
		return isString;
	}

	private static String applyColumnRenamingRule(String column,
			List<ColumnRenamingRule> mapperRule) {
		String calculatedColumnName = column;
		if (mapperRule != null && mapperRule.size() > 0) {
			String replaceString = null;
			for (ColumnRenamingRule rule : mapperRule) {
				replaceString = rule.getReplaceString();
				replaceString = replaceString == null ? "" : replaceString;
				calculatedColumnName = calculatedColumnName.replaceAll(
						rule.getSearchString(), replaceString);
			}
		}
		return calculatedColumnName;
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T toBean(String json, Class<T> clz) {
		return gson.fromJson(json, clz);
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, JsonObject> map = gson.fromJson(json,
				new TypeToken<Map<String, JsonObject>>() {
				}.getType());
		Map<String, T> result = new HashMap<>();
		for (String key : map.keySet()) {
			result.put(key, gson.fromJson(map.get(key), clz));
		}
		return result;
	}

	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = gson.fromJson(json,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		return map;
	}

	public static <T> List<T> toList(String json, Class<T> clz) {
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list = new ArrayList<>();
		for (final JsonElement elem : array) {
			list.add(gson.fromJson(elem, clz));
		}
		return list;
	}

	public static void main(String[] args) {

		List<ColumnRenamingRule> rules = Lists.newArrayList();
		ColumnRenamingRule rule = new ColumnRenamingRule();
		rule.setSearchString("^USER");
		rules.add(rule);

		DbTable dbTable = new DbTable("AMNT_MatchingRule");
		dbTable.setEventType(EventType.UPDATE);
		List<DbRow> rows = Lists.newArrayList();
		DbRow row1 = new DbRow();
		DbRow row2 = new DbRow();

		List<DbColumn> columns = Lists.newArrayList();

		DbColumn column1 = new DbColumn();
		column1.setName("USER_Item_Score_core");
		column1.setValue("\"");
		column1.setMysqlType("varchar(20)");

		DbColumn column2 = new DbColumn();
		column2.setName("USER_Item_Detail_core");
		column2.setMysqlType("bigint(20)");
		column2.setValue("333");
		columns.add(column1);
		columns.add(column2);

		row1.setColumn(columns);
		row2.setColumn(columns);

		rows.add(row1);
		rows.add(row2);
		dbTable.setRows(rows);

		System.out.println(toJson(dbTable, rules));
	}

}