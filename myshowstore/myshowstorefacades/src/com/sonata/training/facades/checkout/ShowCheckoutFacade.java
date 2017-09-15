/**
 *
 */
package com.sonata.training.facades.checkout;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.order.InvalidCartException;


/**
 * @author karthi.m
 *
 */
public interface ShowCheckoutFacade
{

	/**
	 * @param points
	 */
	void addLoyaltyPoints(Integer points);

	/**
	 * @return
	 */
	boolean hasCompletelyPaidByPoints();

	/**
	 * @return
	 */
	OrderData placeShoeOrder() throws InvalidCartException;




}
