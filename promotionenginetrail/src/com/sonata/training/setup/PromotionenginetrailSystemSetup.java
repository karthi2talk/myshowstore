/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sonata.training.setup;

import static com.sonata.training.constants.PromotionenginetrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.sonata.training.constants.PromotionenginetrailConstants;
import com.sonata.training.service.PromotionenginetrailService;


@SystemSetup(extension = PromotionenginetrailConstants.EXTENSIONNAME)
public class PromotionenginetrailSystemSetup
{
	private final PromotionenginetrailService promotionenginetrailService;

	public PromotionenginetrailSystemSetup(final PromotionenginetrailService promotionenginetrailService)
	{
		this.promotionenginetrailService = promotionenginetrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		promotionenginetrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return PromotionenginetrailSystemSetup.class.getResourceAsStream("/promotionenginetrail/sap-hybris-platform.png");
	}
}
