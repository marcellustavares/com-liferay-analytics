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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeyvison Nascimento
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Event {

	public String getAdditionalInfo() {
		return _additionalInfo;
	}

	public String getEvent() {
		return _event;
	}

	public Map<String, String> getProperties() {
		return Collections.unmodifiableMap(_properties);
	}

	public List<Referrer> getReferrers() {
		return Collections.unmodifiableList(_referrers);
	}

	public Date getTimestamp() {
		return _timestamp;
	}

	protected Event() {
	}

	protected void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;
	}

	protected void setEvent(String event) {
		_event = event;
	}

	protected void setReferrers(List<Referrer> referrers) {
		_referrers = referrers;
	}

	protected void setTimestamp(Date timestamp) {
		_timestamp = timestamp;
	}

	private String _additionalInfo;
	private String _event;
	private final Map<String, String> _properties = new HashMap<>();
	private List<Referrer> _referrers = new ArrayList<>();

	@JsonFormat(
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
		shape = JsonFormat.Shape.STRING, timezone = "UTC"
	)
	private Date _timestamp;

}