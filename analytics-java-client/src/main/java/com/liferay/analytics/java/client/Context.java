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

/**
 * @author Jeyvison Nascimento
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Context {

	public long getInstanceId() {
		return _instanceId;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getURL() {
		return _url;
	}

	public long getUserId() {
		return _userId;
	}

	protected Context() {
	}

	protected void setInstanceId(long instanceId) {
		_instanceId = instanceId;
	}

	protected void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	protected void setUrl(String url) {
		_url = url;
	}

	protected void setUserId(long userId) {
		_userId = userId;
	}

	private long _instanceId;
	private String _languageId;
	private String _url;
	private long _userId;

}