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
package com.sonata.training.populators;


import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.ruleengineservices.rao.CustomerReviewRAO;
import de.hybris.platform.ruleengineservices.rao.UserRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Required;


public class MyshowUserRaoPopulator implements Populator<UserModel, UserRAO>
{
	private Converter<CustomerReviewModel, CustomerReviewRAO> customerReviewRaoCovnerter;

	@Override
	public void populate(final UserModel source, final UserRAO target) throws ConversionException
	{
		target.setCustomerReviews(new ArrayList<CustomerReviewRAO>(
				Converters.convertAll(source.getCustomerReviews(), getCustomerReviewRaoCovnerter())));
	}

	public Converter<CustomerReviewModel, CustomerReviewRAO> getCustomerReviewRaoCovnerter()
	{
		return customerReviewRaoCovnerter;
	}

	@Required
	public void setCustomerReviewRaoCovnerter(final Converter<CustomerReviewModel, CustomerReviewRAO> customerReviewRaoCovnerter)
	{
		this.customerReviewRaoCovnerter = customerReviewRaoCovnerter;
	}
}