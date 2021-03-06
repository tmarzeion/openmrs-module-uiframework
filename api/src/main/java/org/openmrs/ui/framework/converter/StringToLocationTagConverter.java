/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.ui.framework.converter;

import org.apache.commons.lang.StringUtils;
import org.openmrs.LocationTag;
import org.openmrs.api.LocationService;
import org.openmrs.ui.framework.converter.util.ConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocationTagConverter implements Converter<String, LocationTag> {
	
	@Autowired
	@Qualifier("locationService")
	public LocationService service;
	
	@Override
	public LocationTag convert(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		} else if (ConversionUtil.onlyDigits(source)) {
			return service.getLocationTag(Integer.valueOf(source));
		}
		return service.getLocationTagByUuid(source);
	}
}
