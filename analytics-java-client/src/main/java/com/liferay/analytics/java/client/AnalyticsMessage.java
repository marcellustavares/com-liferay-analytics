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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeyvison Nascimento
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageFormat",
	use = JsonTypeInfo.Id.NAME, visible = true
)
public final class AnalyticsMessage {

	public void addEvent(Event event) {
		_events .add(event);
	}

	public String getAnalyticsKey() {
		return _analyticsKey;
	}

	public long getAnonymousUserId() {
		return _anonymousUserId;
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public String getChannel() {
		return _channel;
	}

	public String getClientIP() {
		return _clientIP;
	}

	public Context getContext() {
		return _context;
	}

	public List<Event> getEvents() {
		return _events;
	}

	public String getMessageFormat() {
		return _messageFormat;
	}

	public String getProtocolVersion() {
		return _protocolVersion;
	}

	public String getUserAgent() {
		return _userAgent;
	}

	protected AnalyticsMessage() {
	}

	protected void setAnalyticsKey(String analyticsKey) {
		_analyticsKey = analyticsKey;
	}

	protected void setAnonymousUserId(long anonymousUserId) {
		_anonymousUserId = anonymousUserId;
	}

	protected void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	protected void setChannel(String channel) {
		_channel = channel;
	}

	protected void setClientIP(String clientIP) {
		_clientIP = clientIP;
	}

	protected void setContext(Context context) {
		_context = context;
	}

	protected void setMessageFormat(String messageFormat) {
		_messageFormat = messageFormat;
	}

	protected void setProtocolVersion(String protocolVersion) {
		_protocolVersion = protocolVersion;
	}

	protected void setUserAgent(String userAgent) {
		_userAgent = userAgent;
	}

	private String _analyticsKey;
	private long _anonymousUserId;
	private String _applicationId;
	private String _channel;
	private String _clientIP;
	private Context _context;
	private final List<Event> _events = new ArrayList<>();
	private String _messageFormat;
	private String _protocolVersion;
	private String _userAgent;

}