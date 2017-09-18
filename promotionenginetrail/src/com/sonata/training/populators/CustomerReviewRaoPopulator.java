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

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.ruleengineservices.rao.CustomerReviewRAO;
import de.hybris.platform.ruleengineservices.rao.ProductRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 *
 */
public class CustomerReviewRaoPopulator implements Populator<CustomerReviewModel, CustomerReviewRAO>
{

	private Converter<ProductModel, ProductRAO> productRaoConverter;


	@Override
	public void populate(final CustomerReviewModel source, final CustomerReviewRAO target) throws ConversionException
	{
		target.setProduct(productRaoConverter.convert(source.getProduct()));
	}

	/**
	 * @return the productRaoConverter
	 */
	public Converter<ProductModel, ProductRAO> getProductRaoConverter()
	{
		return productRaoConverter;
	}

	/**
	 * @param productRaoConverter
	 *           the productRaoConverter to set
	 */
	public void setProductRaoConverter(final Converter<ProductModel, ProductRAO> productRaoConverter)
	{
		this.productRaoConverter = productRaoConverter;
	}

}
