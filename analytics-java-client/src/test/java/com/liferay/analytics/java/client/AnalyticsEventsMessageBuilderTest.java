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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jeyvison Nascimento
 * @author Marcellus Tavares
 */
public class AnalyticsEventsMessageBuilderTest {

	@Test
	public void testCreateContext() {
		long expectedInstanceId = randomLong();
		String expectedLanguageId = randomString();
		String expectedURL = randomString();
		long expectedUserId = randomLong();

		Context actualContext = createContext(
			expectedInstanceId, expectedLanguageId, expectedURL,
			expectedUserId);

		assertContext(
			expectedInstanceId, expectedLanguageId, expectedURL, expectedUserId,
			actualContext);
	}

	@Test
	public void testCreateEvent() {
		String expectedAdditionalInfo = randomString();
		String expectedEvent = randomString();

		Map<String, String> expectedProperties = new HashMap<>();

		expectedProperties.put(randomString(), randomString());
		expectedProperties.put(randomString(), randomString());
		expectedProperties.put(randomString(), randomString());

		List<Referrer> expectedReferrers = new ArrayList<>();

		List<String> expectedReferrerEntityIds = Arrays.asList(
			randomString(), randomString(), randomString());

		String expectedReferrerEntityType = randomString();

		Referrer expectedReferrer = createReferrer(
			expectedReferrerEntityIds, expectedReferrerEntityType);

		expectedReferrers.add(expectedReferrer);

		Date expectedTimestamp = new Date();

		Event actualEvent = createEvent(
			expectedAdditionalInfo, expectedEvent, expectedProperties,
			expectedReferrers, expectedTimestamp);

		assertEvent(
			expectedAdditionalInfo, expectedEvent, expectedProperties,
			expectedReferrers, expectedTimestamp, actualEvent);
	}

	@Test
	public void testCreateMessage() {

		// Context

		Context expectedContext = createContext(
			123, "en_US", "http://www.liferay.com", 456);

		// Events

		List<Event> expectedEvents = new ArrayList<>();

		String expectedAdditionalInfo = randomString();
		String expectedEventName = randomString();

		Map<String, String> expectedProperties = new HashMap<>();

		expectedProperties.put(randomString(), randomString());

		List<Referrer> expectedReferrers = new ArrayList<>();

		expectedReferrers.add(
			createReferrer(
				Arrays.asList(randomString(), randomString(), randomString()),
				randomString()));

		Date expectedTimestamp = new Date();

		expectedEvents.add(
			createEvent(
				expectedAdditionalInfo, expectedEventName, expectedProperties,
				expectedReferrers, expectedTimestamp));

		// Message

		String expectedAnalyticsKey = randomString();
		long expectedAnonymousUserId = randomLong();
		String expectedApplicationId = randomString();
		String expectedClientIP = randomString();
		String expectedMessageFormat = randomString();
		String expectedProtocolVersion = randomString();
		String expectedUserAgent = randomString();

		AnalyticsMessage actualAnalyticsEventsMessage =
			createAnalyticsEventsMessage(
				expectedAnalyticsKey, expectedAnonymousUserId,
				expectedApplicationId, expectedClientIP, expectedContext,
				expectedEvents, expectedMessageFormat, expectedProtocolVersion,
				expectedUserAgent);

		Assert.assertEquals(
			expectedAnonymousUserId,
			actualAnalyticsEventsMessage.getAnonymousUserId());
		Assert.assertEquals(
			expectedAnalyticsKey,
			actualAnalyticsEventsMessage.getAnalyticsKey());
		Assert.assertEquals(
			expectedApplicationId,
			actualAnalyticsEventsMessage.getApplicationId());
		Assert.assertEquals(
			expectedClientIP, actualAnalyticsEventsMessage.getClientIP());

		assertContext(
			expectedContext.getInstanceId(), expectedContext.getLanguageId(),
			expectedContext.getURL(), expectedContext.getUserId(),
			actualAnalyticsEventsMessage.getContext());

		List<Event> actualEvents = actualAnalyticsEventsMessage.getEvents();

		Assert.assertEquals(
			expectedEvents.toString(), expectedEvents.size(),
			actualEvents.size());

		int i = 0;

		for (Event expectedEvent : expectedEvents) {
			assertEvent(
				expectedEvent.getAdditionalInfo(), expectedEvent.getEvent(),
				expectedEvent.getProperties(), expectedEvent.getReferrers(),
				expectedEvent.getTimestamp(), actualEvents.get(i++));
		}

		Assert.assertEquals(
			expectedMessageFormat,
			actualAnalyticsEventsMessage.getMessageFormat());
		Assert.assertEquals(
			expectedProtocolVersion,
			actualAnalyticsEventsMessage.getProtocolVersion());
		Assert.assertEquals(
			expectedUserAgent, actualAnalyticsEventsMessage.getUserAgent());
	}

	@Test
	public void testCreateReferrer() {
		List<String> expectedReferrerEntityIds = Arrays.asList(
			randomString(), randomString(), randomString());

		String expectedReferrerEntityType = randomString();

		Referrer referrer = createReferrer(
			expectedReferrerEntityIds, expectedReferrerEntityType);

		assertReferrer(
			expectedReferrerEntityIds, expectedReferrerEntityType, referrer);
	}

	protected void assertContext(
		long expectedInstanceId, String expectedLanguageId, String expectedURL,
		long expectedUserId, Context actualContext) {

		Assert.assertEquals(expectedInstanceId, actualContext.getInstanceId());
		Assert.assertEquals(expectedLanguageId, actualContext.getLanguageId());
		Assert.assertEquals(expectedURL, actualContext.getURL());
		Assert.assertEquals(expectedUserId, actualContext.getUserId());
	}

	protected void assertEvent(
		String expectedAdditionalInfo, String expectedEvent,
		Map<String, String> expectedProperties,
		List<Referrer> expectedReferrers, Date expectedTimestamp,
		Event actualEvent) {

		Assert.assertEquals(
			expectedAdditionalInfo, actualEvent.getAdditionalInfo());
		Assert.assertEquals(expectedEvent, actualEvent.getEvent());
		Assert.assertEquals(expectedProperties, actualEvent.getProperties());

		List<Referrer> actualReferrers = actualEvent.getReferrers();

		Assert.assertEquals(
			expectedReferrers.toString(), expectedReferrers.size(),
			actualReferrers.size());

		int i = 0;

		for (Referrer expectedReferrer : expectedReferrers) {
			assertReferrer(
				expectedReferrer.getReferrerEntityIds(),
				expectedReferrer.getReferrerEntityType(),
				actualReferrers.get(i++));
		}

		Assert.assertEquals(expectedTimestamp, actualEvent.getTimestamp());
	}

	protected void assertReferrer(
		List<String> expectedReferrerEntityIds,
		String expectedReferrerEntityType, Referrer actualReferrer) {

		Assert.assertEquals(
			expectedReferrerEntityIds, actualReferrer.getReferrerEntityIds());
		Assert.assertEquals(
			expectedReferrerEntityType, actualReferrer.getReferrerEntityType());
	}

	protected AnalyticsMessage createAnalyticsEventsMessage(
		String analyticsKey, long anonymousUserId, String applicationId,
		String clientIP, Context context, List<Event> events,
		String messageFormat, String protocolVersion, String userAgent) {

		AnalyticsMessageBuilder messageBuilder =
			AnalyticsMessageBuilder.builder();

		messageBuilder.setAnalyticsKey(analyticsKey);
		messageBuilder.setAnonymousUserId(anonymousUserId);
		messageBuilder.setApplicationId(applicationId);
		messageBuilder.setClientIP(clientIP);
		messageBuilder.setContext(context);

		for (Event event : events) {
			messageBuilder.addEvent(event);
		}

		messageBuilder.setMessageFormat(messageFormat);
		messageBuilder.setProtocolVersion(protocolVersion);
		messageBuilder.setUserAgent(userAgent);

		return messageBuilder.build();
	}

	protected Context createContext(
		long instanceId, String languageId, String url, long userId) {

		AnalyticsMessageBuilder messageBuilder =
			AnalyticsMessageBuilder.builder();

		AnalyticsMessageBuilder.ContextBuilder contextBuilder =
			messageBuilder.newContextBuilder();

		contextBuilder.setInstanceId(instanceId);
		contextBuilder.setLanguageId(languageId);
		contextBuilder.setUrl(url);
		contextBuilder.setUserId(userId);

		return contextBuilder.build();
	}

	protected Event createEvent(
		String additionalInfo, String event, Map<String, String> properties,
		List<Referrer> referrers, Date timestamp) {

		AnalyticsMessageBuilder messageBuilder =
			AnalyticsMessageBuilder.builder();

		AnalyticsMessageBuilder.EventBuilder eventBuilder =
			messageBuilder.newEventBuilder();

		eventBuilder.setAdditionalInfo(additionalInfo);
		eventBuilder.setEvent(event);

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			eventBuilder.addProperty(entry.getKey(), entry.getValue());
		}

		for (Referrer referrer : referrers) {
			eventBuilder.addReferrer(referrer);
		}

		eventBuilder.setTimestamp(timestamp);

		return eventBuilder.build();
	}

	protected Referrer createReferrer(
		List<String> referrerEntityIds, String referrerEntityType) {

		AnalyticsMessageBuilder messageBuilder =
			AnalyticsMessageBuilder.builder();

		AnalyticsMessageBuilder.ReferrerBuilder referrerBuilder =
			messageBuilder.newReferrerBuilder();

		referrerBuilder.setReferrerEntityIds(referrerEntityIds);
		referrerBuilder.setReferrerEntityType(referrerEntityType);

		return referrerBuilder.build();
	}

	protected long randomLong() {
		return RandomUtils.nextLong();
	}

	protected String randomString() {
		return RandomStringUtils.random(5);
	}

}