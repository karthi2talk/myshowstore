/**
 *
 */
package com.sonata.training.facades.checkout.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;

import com.sonata.training.facades.checkout.ShowCheckoutFacade;


/**
 * @author karthi.m
 *
 */
public class ShowCheckoutFacadeImpl extends DefaultCheckoutFacade implements ShowCheckoutFacade
{


	@Override
	public void addLoyaltyPoints(final Integer points)
	{
		final CartModel cartModel = getCartService().getSessionCart();
		cartModel.setLoyaltyPoints(points);
		getModelService().save(cartModel);
		getCartService().setSessionCart(cartModel);
	}

	@Override
	public boolean hasCompletelyPaidByPoints()
	{
		final CartModel cartModel = getCartService().getSessionCart();
		if (cartModel.getRevisedTotal() != null && cartModel.getRevisedTotal().doubleValue() <= 0)
		{
			setPaymentAndBillingInfoForPaymentMadeByPointsCart(cartModel);
			return true;
		}
		return false;
	}

	/**
	 * @param cartModel
	 */
	private void setPaymentAndBillingInfoForPaymentMadeByPointsCart(final CartModel cartModel)
	{
		cartModel.setPaymentInfo(null);

	}


	@Override
	public OrderData placeShoeOrder() throws InvalidCartException
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (cartModel.getUser().equals(getCurrentUserForCheckout()) || getCheckoutCustomerStrategy().isAnonymousCheckout())
			{
				beforePlaceOrder(cartModel);
				final OrderModel orderModel = placeOrder(cartModel);
				setCustomerLoyaltyPoints(orderModel);
				afterPlaceOrder(cartModel, orderModel);
				if (orderModel != null)
				{
					return getOrderConverter().convert(orderModel);
				}
			}
		}
		return null;
	}

	/**
	 * @param orderModel
	 */
	private void setCustomerLoyaltyPoints(final OrderModel orderModel)
	{
		final CustomerModel customerModel = getCurrentUserForCheckout();
		if (orderModel.getLoyaltyPoints() != null && orderModel.getLoyaltyPoints().intValue() > 0)
		{
			final int modifiedLoyaltyPoints = customerModel.getLoyaltyPoints().intValue() - orderModel.getLoyaltyPoints().intValue();
			customerModel.setLoyaltyPoints(new Integer(modifiedLoyaltyPoints));
			getModelService().save(customerModel);
			getUserService().setCurrentUser(customerModel);

		}

	}





}
