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

package com.liferay.analytics.messaging.internal.messaging;

import com.liferay.analytics.messaging.constants.AnalyticsDestinationNames;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class AnalyticsMessagingConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		registerPortalAnalyticsDestination();
	}

	@Deactivate
	protected void deactivate() {
		unregisterPortalAnalyticsDestination();

		_bundleContext = null;
	}

	protected void registerPortalAnalyticsDestination() {
		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createParallelDestinationConfiguration(
				AnalyticsDestinationNames.ANALYTICS);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("destination.name", destination.getName());

		_serviceRegistration = _bundleContext.registerService(
			Destination.class, destination, properties);
	}

	protected void unregisterPortalAnalyticsDestination() {
		Destination destination = _bundleContext.getService(
			_serviceRegistration.getReference());

		destination.destroy();

		_serviceRegistration.unregister();

		_serviceRegistration = null;
	}

	private BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}