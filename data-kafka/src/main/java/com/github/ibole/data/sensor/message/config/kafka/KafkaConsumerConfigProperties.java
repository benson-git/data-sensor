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

package com.github.ibole.data.sensor.message.config.kafka;

import com.google.common.base.MoreObjects;

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
public class KafkaConsumerConfigProperties {

	private String groupId;
	private boolean autoCommitOffset;
	private Long pollTimeout;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean isAutoCommitOffset() {
		return autoCommitOffset;
	}

	public void setAutoCommitOffset(boolean autoCommitOffset) {
		this.autoCommitOffset = autoCommitOffset;
	}

	public Long getPollTimeout() {
		return pollTimeout;
	}

	public void setPollTimeout(Long pollTimeout) {
		this.pollTimeout = pollTimeout;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("groupId", groupId)
				.add("autoCommitOffset", autoCommitOffset)
				.add("pollTimeout", pollTimeout).toString();
	}

}