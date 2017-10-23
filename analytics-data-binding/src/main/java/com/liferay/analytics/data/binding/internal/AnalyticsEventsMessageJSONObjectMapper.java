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

package com.liferay.analytics.data.binding.internal;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.analytics.data.binding.JSONObjectMapper;
import com.liferay.analytics.java.client.AnalyticsEventsMessage;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"model=com.liferay.analytics.java.client.AnalyticsEventsMessage"
	},
	service = JSONObjectMapper.class
)
public class AnalyticsEventsMessageJSONObjectMapper
	implements JSONObjectMapper<AnalyticsEventsMessage> {

	@Override
	public String map(AnalyticsEventsMessage analyticsEventsMessage)
		throws IOException {

		return _objectMapper.writeValueAsString(analyticsEventsMessage);
	}

	@Override
	public AnalyticsEventsMessage map(String jsonString) throws IOException {
		return _objectMapper.readValue(
			jsonString, AnalyticsEventsMessage.class);
	}

	private final ObjectMapper _objectMapper = new ObjectMapper();

}