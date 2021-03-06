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

import com.github.ibole.data.sensor.common.handler.event.Processor;
import com.github.ibole.data.sensor.common.model.canal.EventType;

import java.util.HashMap;
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
public abstract class EventHandler extends AbstractHandler {

	private Map<EventType, Processor> processors = new HashMap<>();

	/*
	 * @see
	 * com.github.ibole.data.sensor.common.handler.AbstractHandler#doProcess
	 * (com.github.ibole.data.sensor.common.handler.RuntimeData)
	 */
	@Override
	public void doProcess(RuntimeData runtimeData) {
		EventType eventType = runtimeData.getDbTable().getEventType();
		Processor processor = processors.get(eventType);
		if (processor != null) {
			processor.process(runtimeData);
		} else {
			this.logger.warn("No processor found for event type '{}'",
					eventType);
		}
	}

	public Map<EventType, Processor> getProcessors() {
		return processors;
	}

}
