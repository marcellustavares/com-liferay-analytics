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
import java.util.List;

/**
 * @author  Jeyvison Nascimento
 */
public class AnalyticsMessageBuilder {

	public static AnalyticsMessageBuilder builder() {
		return new AnalyticsMessageBuilder();
	}

	public AnalyticsMessage build() {
		return _analyticsMessage;
	}

	public ContextBuilder newContextBuilder() {
		return new ContextBuilder();
	}

	public ReferrerBuilder newReferrerBuilder() {
		return new ReferrerBuilder();
	}

	public AnalyticsMessageBuilder setAnalyticsKey(String analyticsKey) {
		_analyticsMessage.setAnalyticsKey(analyticsKey);

		return this;
	}

	public AnalyticsMessageBuilder setAnonymousUserId(long anonymousUserId) {
		_analyticsMessage.setAnonymousUserId(anonymousUserId);

		return this;
	}

	public AnalyticsMessageBuilder setApplicationId(String applicationId) {
		_analyticsMessage.setApplicationId(applicationId);

		return this;
	}

	public AnalyticsMessageBuilder setChannel(String channel) {
		_analyticsMessage.setChannel(channel);

		return this;
	}

	public AnalyticsMessageBuilder setClientIP(String clientIP) {
		_analyticsMessage.setClientIP(clientIP);

		return this;
	}

	public AnalyticsMessageBuilder setContext(Context context) {
		_analyticsMessage.setContext(context);

		return this;
	}

	public AnalyticsMessageBuilder setEvent(Event event) {
		_analyticsMessage.addEvent(event);

		return this;
	}

	public AnalyticsMessageBuilder setMessageFormat(String messageFormat) {
		_analyticsMessage.setMessageFormat(messageFormat);

		return this;
	}

	public AnalyticsMessageBuilder setProtocolVersion(String protocolVersion) {
		_analyticsMessage.setProtocolVersion(protocolVersion);

		return this;
	}

	public AnalyticsMessageBuilder setUserAgent(String userAgent) {
		_analyticsMessage.setUserAgent(userAgent);

		return this;
	}

	public class ContextBuilder {

		public Context build() {
			return _context;
		}

		public ContextBuilder setInstanceId(long instanceId) {
			_context.setInstanceId(instanceId);

			return this;
		}

		public ContextBuilder setLanguageId(String languageId) {
			_context.setLanguageId(languageId);

			return this;
		}

		public ContextBuilder setUrl(String url) {
			_context.setUrl(url);

			return this;
		}

		public ContextBuilder setUserId(long userId) {
			_context.setUserId(userId);

			return this;
		}

		private ContextBuilder() {
		}

		private Context _context;

	}

	public class EventBuilder {

		public Event build() {
			return _event;
		}

		public EventBuilder seReferrers(List<Referrer> referrers) {
			_event.setReferrers(referrers);

			return this;
		}

		public EventBuilder setAdditionalInfo(String additionalInfo) {
			_event.setAdditionalInfo(additionalInfo);

			return this;
		}

		public EventBuilder setEvent(String event) {
			_event.setEvent(event);

			return this;
		}

		public EventBuilder setTimestamp(Date timestamp) {
			_event.setTimestamp(timestamp);

			return this;
		}

		private EventBuilder() {
			_event = new Event();
		}

		private final Event _event;

	}

	public class ReferrerBuilder {

		public Referrer build() {
			return _referrer;
		}

		public void setReferrerEntityIds(List<String> referrerEntityIds) {
			_referrer.setReferrerEntityIds(referrerEntityIds);
		}

		public void setReferrerEntityType(String referrerEntityType) {
			_referrer.setReferrerEntityType(referrerEntityType);
		}

		private ReferrerBuilder() {
			_referrer = new Referrer();
		}

		private final Referrer _referrer;

	}

	private AnalyticsMessageBuilder() {
		_analyticsMessage = new AnalyticsMessage();
	}

	private final AnalyticsMessage _analyticsMessage;

}