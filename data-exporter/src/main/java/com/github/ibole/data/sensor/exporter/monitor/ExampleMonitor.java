package com.github.ibole.data.sensor.exporter.monitor;

import com.github.ibole.data.sensor.common.monitor.AbstractMonitor;
import com.github.ibole.data.sensor.common.monitor.Monitor;
import com.github.ibole.data.sensor.common.monitor.RuntimeData;


/**
 * 
 * 
 * @author bwang
 *
 */
public class ExampleMonitor extends AbstractMonitor implements Monitor {

  @Override
  public void doProcess(RuntimeData runtimeData) {

    logger.info("执行Handler '{} '", this.getClass().getName());
   
  }


}
