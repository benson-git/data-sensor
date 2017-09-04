package com.github.ibole.data.sensor.exporter.handler;

import com.github.ibole.data.sensor.common.handler.AbstractHandler;
import com.github.ibole.data.sensor.common.handler.Handler;
import com.github.ibole.data.sensor.common.handler.RuntimeData;


/**
 * 
 * 
 * @author bwang
 *
 */
public class ExampleHandler extends AbstractHandler implements Handler {

  @Override
  public void doHandle(RuntimeData runtimeData) {

    logger.info("执行Handler '{} '", this.getClass().getName());
   
  }


}
