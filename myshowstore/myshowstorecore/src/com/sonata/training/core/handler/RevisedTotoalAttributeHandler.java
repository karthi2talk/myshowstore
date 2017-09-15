/**
 *
 */
package com.sonata.training.core.handler;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.apache.log4j.Logger;


/**
 * @author karthi.m
 *
 */
public class RevisedTotoalAttributeHandler implements DynamicAttributeHandler<Double, AbstractOrderModel>
{

	private final static Logger LOG = Logger.getLogger(RevisedTotoalAttributeHandler.class);

	@Override
	public Double get(final AbstractOrderModel model)
	{
		try
		{
			final Integer loyaltyPoints = model.getLoyaltyPoints();
			if (loyaltyPoints != null)
			{
				return new Double(model.getTotalPrice().doubleValue() - loyaltyPoints.doubleValue());
			}
		}
		catch (final Exception e)
		{
			LOG.error("Error in getting revisedTotal", e);
		}
		return new Double(0d);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final AbstractOrderModel paramMODEL, final Double paramVALUE)
	{
		// YTODO Auto-generated method stub

	}

}
