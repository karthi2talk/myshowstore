/**
 *
 */
package com.sonata.trining.facades.populators;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author karthi.m
 *
 */
public class ShowCartPopulator implements Populator<CartModel, CartData>
{
	private PriceDataFactory priceDataFactory;

	@Override
	public void populate(final CartModel source, final CartData target) throws ConversionException
	{

		if (source != null)
		{
			Integer loyaltyPoints = new Integer(0);
			if (source.getLoyaltyPoints() != null)
			{
				loyaltyPoints = source.getLoyaltyPoints();
			}
			target.setLoyaltyPoints(loyaltyPoints);
			target.setRevisedTotal(createPrice(source, source.getRevisedTotal()));
		}
	}

	protected PriceData createPrice(final CartModel source, final Double val)
	{
		if (source == null)
		{
			throw new IllegalArgumentException("source order must not be null");
		}

		final CurrencyModel currency = source.getCurrency();
		if (currency == null)
		{
			throw new IllegalArgumentException("source order currency must not be null");
		}

		// Get double value, handle null as zero
		final double priceValue = val != null ? val.doubleValue() : 0d;

		return getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(priceValue), currency);
	}

	protected PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

}
