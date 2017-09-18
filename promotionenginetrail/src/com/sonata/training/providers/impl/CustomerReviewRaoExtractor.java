/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.sonata.training.providers.impl;

import static com.google.common.base.Preconditions.checkArgument;

import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rao.providers.RAOFactsExtractor;

import java.util.HashSet;
import java.util.Set;


/**
 *
 */
public class CustomerReviewRaoExtractor implements RAOFactsExtractor
{

	public static final String EXPAND_CUSTOMER_REVIEWS = "EXPAND_CUSTOMER_REVIEWS";

	@Override
	public Set<?> expandFact(final Object fact)
	{
		checkArgument(fact instanceof CartRAO, "CartRAO type is expected here)");

		if (fact instanceof CartRAO)
		{
			final Set<Object> facts = new HashSet<>();
			final CartRAO cartRAO = (CartRAO) fact;
			if (null != cartRAO.getUser())
			{
				facts.addAll(cartRAO.getUser().getCustomerReviews());
			}
			return facts;
		}
		return null;
	}

	@Override
	public String getTriggeringOption()
	{
		return EXPAND_CUSTOMER_REVIEWS;
	}

	@Override
	public boolean isMinOption()
	{
		return false;
	}

	@Override
	public boolean isDefault()
	{
		return true;
	}
}