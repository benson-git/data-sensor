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
package com.github.ibole.data.sensor.exporter.pipeline;

import com.github.ibole.data.sensor.common.monitor.Monitor;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2016, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * 管道，先通过setBasic设置基础处理器Handler，
 * 接着按顺序添加其他处理器，执行时的顺序是：先添加进来的先执行，最后才执行基础处理器。
 * 
 * @author bwang
 *
 */
public class StandardPipeline implements Pipeline {
  
  protected Monitor first = null;
  //基础处理器，最后被处理.
  protected Monitor basic = null;

  public void addHandler(Monitor handler) {
    if (first == null) {
      first = handler;
      handler.setNext(basic);
    } else {
      Monitor current = first;
      while (current != null) {
        if (current.getNext() == basic) {
          current.setNext(handler);
          handler.setNext(basic);
          break;
        }
        current = current.getNext();
      }
    }
  }

  public Monitor getBasic() {
    return basic;
  }

  public Monitor getFirst() {
    return first;
  }

  public void setBasic(Monitor handler) {
    this.basic = handler;
  }

}
