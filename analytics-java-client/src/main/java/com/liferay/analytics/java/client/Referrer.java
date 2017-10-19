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

import java.util.Collections;
import java.util.List;

/**
 * @author Jeyvison Nascimento
 */
public final class Referrer {

	public List<String> getReferrerEntityIds() {
		return _referrerEntityIds;
	}

	public String getReferrerEntityType() {
		return _referrerEntityType;
	}

	protected Referrer() {
	}

	protected void setReferrerEntityIds(List<String> referrerEntityIds) {
		_referrerEntityIds = referrerEntityIds;
	}

	protected void setReferrerEntityType(String referrerEntityType) {
		_referrerEntityType = referrerEntityType;
	}

	private List<String> _referrerEntityIds = Collections.emptyList();
	private String _referrerEntityType;

}