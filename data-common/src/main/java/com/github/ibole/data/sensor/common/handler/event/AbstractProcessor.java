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

package com.github.ibole.data.sensor.common.handler.event;

import com.github.ibole.data.sensor.common.handler.RuntimeData;
import com.github.ibole.data.sensor.common.util.ClassHelper;
import com.github.ibole.data.sensor.common.util.JsonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2017, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * @param <M>
 *            M - model
 * @author bwang
 *
 */
public abstract class AbstractProcessor<M> implements Processor {

    /**
     * 处理运行时数据.
     * @param runtimeData 运行时数据.
     */
	@Override
	public void process(RuntimeData runtimeData) {

		Class<M> clazz = ClassHelper.getClassGenricType(getClass());
		String json = JsonUtil.toJson(runtimeData.getDbTable(),
				runtimeData.getMapperRule());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		List<M> models = gson.fromJson(json,
				TypeToken.getParameterized(List.class, clazz).getType());
		doProcess(models);
	}

	/**
	 * @param model
	 */
	public abstract void doProcess(List<M> models);

}
