package com.github.ibole.data.sensor.exporter.handler.example;

import com.github.ibole.data.sensor.common.handler.EventHandler;
import com.github.ibole.data.sensor.common.model.canal.EventType;

/**
 * 
 * 
 * @author bwang
 *
 */
public class ExampleEventHandler extends EventHandler {

	/*
	 * @see
	 * com.github.ibole.data.sensor.common.handler.EventHandler#registerProcessor
	 * ()
	 */
	@Override
	public void registerProcessor() {
		getProcessors().put(EventType.DELETE, new ExampleUpdateProcessor());

	}

}
