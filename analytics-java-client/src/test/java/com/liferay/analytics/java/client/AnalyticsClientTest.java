/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.analytics.java.client;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Eduardo Garcia
 */
@Ignore
public class AnalyticsClientTest {

	@Test
	public void testAnalyticsEventCreation() {
		AnalyticsMessageBuilder analyticsMessageBuilder =
			AnalyticsMessageBuilder.builder();

		analyticsMessageBuilder.setAnalyticsKey("WXYZ");
		analyticsMessageBuilder.setApplicationId("AT");
		analyticsMessageBuilder.setChannel("web");
		analyticsMessageBuilder.setMessageFormat("AT");

		AnalyticsMessageBuilder.ContextBuilder contextBuilder =
			analyticsMessageBuilder.newContextBuilder();

		contextBuilder.setInstanceId(1234);
		contextBuilder.setLanguageId("en_US");
		contextBuilder.setUrl("http://www.liferay.com");
		contextBuilder.setUserId(1234);

		analyticsMessageBuilder.setContext(contextBuilder.build());

		AnalyticsMessageBuilder.EventBuilder eventBuilder =
			analyticsMessageBuilder.newEventBuilder();

		eventBuilder.setEvent("view");
		eventBuilder.addProperty("elementId", "banner1");
		eventBuilder.setTimestamp(new Date());

		analyticsMessageBuilder.addEvent(eventBuilder.build());

		Response response = _analyticsClient.sendAnalytics(
			analyticsMessageBuilder.build());

		Assert.assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	private final AnalyticsClient _analyticsClient = new AnalyticsClient();

}