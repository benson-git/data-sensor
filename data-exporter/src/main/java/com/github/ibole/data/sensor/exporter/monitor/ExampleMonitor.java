package com.github.ibole.data.sensor.exporter.monitor;

import com.github.ibole.data.sensor.common.handler.AbstractHandler;
import com.github.ibole.data.sensor.common.handler.Handler;
import com.github.ibole.data.sensor.common.handler.RuntimeData;


/**
 * 
 * 
 * @author bwang
 *
 */
public class ExampleMonitor extends AbstractHandler implements Handler {

  @Override
  public void doProcess(RuntimeData runtimeData) {

    logger.info("执行Handler '{} '", this.getClass().getName());
   
  }


}
