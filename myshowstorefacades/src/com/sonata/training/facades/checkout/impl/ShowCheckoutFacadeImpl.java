/**
 *
 */
package com.sonata.training.facades.checkout.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.util.Utilities;

import org.apache.log4j.Logger;

import com.sonata.training.facades.checkout.ShowCheckoutFacade;


/**
 * @author karthi.m
 *
 */
public class ShowCheckoutFacadeImpl extends DefaultCheckoutFacade implements ShowCheckoutFacade
{

	private static final Logger LOG = Logger.getLogger(ShowCheckoutFacadeImpl.class);

	private StockService stockService;

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
				updateProductStockLevels(orderModel);
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
	private void updateProductStockLevels(final OrderModel orderModel)
	{
		for (final AbstractOrderEntryModel orderEntry : orderModel.getEntries())
		{
			try
			{
				for (final ConsignmentEntryModel consignmentEntry : orderEntry.getConsignmentEntries())
				{
					final WarehouseModel wareHouseModel = consignmentEntry.getConsignment().getWarehouse();

					final StockLevelModel stockLevel = stockService.getStockLevel(orderEntry.getProduct(), wareHouseModel);
					final int updatedAvailableQuantity = stockLevel.getAvailable() - orderEntry.getQuantity().intValue();
					stockLevel.setAvailable(updatedAvailableQuantity);
					getModelService().save(stockLevel);
					Utilities.invalidateCache(stockLevel.getPk());
					getModelService().refresh(stockLevel);
				}
			}
			catch (final Exception ex)
			{
				LOG.error("Error in updating product stock level for the product " + orderEntry.getProduct().getCode());
			}
		}

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

	/**
	 * @return the stockService
	 */
	public StockService getStockService()
	{
		return stockService;
	}

	/**
	 * @param stockService
	 *           the stockService to set
	 */
	public void setStockService(final StockService stockService)
	{
		this.stockService = stockService;
	}






}
