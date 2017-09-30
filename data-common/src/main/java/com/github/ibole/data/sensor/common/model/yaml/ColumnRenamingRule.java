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

package com.github.ibole.data.sensor.common.model.yaml;

import com.google.common.base.MoreObjects;

/*********************************************************************************************.
 * 
 * 
 * <p>Copyright 2017, iBole Inc. All rights reserved.
 * 
 * <p></p>
 *********************************************************************************************/

/**
 * This class is used to specify a renaming rule for columns in a table. This
 * renaming rule will be run against all column names before calculating the
 * corresponding property name. The most common use case is when columns in a
 * table are all prefixed by a certain value.
 * 
 * For example, if columns in a table are named:
 * 
 * <ul>
 * <li>CUST_NAME</li>
 * <li>CUST_ADDRESS</li>
 * <li>CUST_CITY</li>
 * <li>CUST_STATE</li>
 * </ul>
 * 
 * it might be annoying to have the generated properties all containing the CUST
 * prefix. This class can be used to remove the prefix by specifying
 * 
 * <ul>
 * <li>searchString = "^CUST"</li>
 * <li>replaceString=""</li>
 * </ul>
 * 
 * Note that internally, the generator uses the
 * <code>java.util.regex.Matcher.replaceAll</code> method for this function. See
 * the documentation of that method for example of the regular expression
 * language used in Java.
 * 
 * @author bwang
 *
 */
public class ColumnRenamingRule {
	private String searchString;
	private String replaceString;

	public String getReplaceString() {
		return replaceString;
	}

	public void setReplaceString(String replaceString) {
		this.replaceString = replaceString;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("searchString", searchString)
				.add("replaceString", replaceString).toString();

	}
}
