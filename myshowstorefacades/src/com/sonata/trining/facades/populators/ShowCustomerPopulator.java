/**
 *
 */
package com.sonata.trining.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author karthi.m
 *
 */
public class ShowCustomerPopulator implements Populator<CustomerModel, CustomerData>
{

	@Override
	public void populate(final CustomerModel source, final CustomerData target) throws ConversionException
	{

		if (source != null)
		{
			target.setLoyalityPoints(source.getLoyalityPoints());
		}
	}

}
