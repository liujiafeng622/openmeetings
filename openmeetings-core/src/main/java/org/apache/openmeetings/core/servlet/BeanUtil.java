/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") +  you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openmeetings.core.servlet;

import javax.servlet.ServletContext;

import org.apache.openmeetings.util.InitializationContainer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BeanUtil {
	public <T> T getBean(Class<T> beanClass, ServletContext ctx) throws ServerNotInitializedException {
		if (InitializationContainer.initComplete) {
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(ctx);
			if (wc == null) {
				throw new ServerNotInitializedException("Server not yet initialized (context is null), retry in couple of seconds");
			}
			return wc.getBean(beanClass);
		} else {
			throw new ServerNotInitializedException("Server not yet initialized, retry in couple of seconds");
		}
	}
}
