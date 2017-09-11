/**
 *
 */
package com.sonata.training.facades.checkout;

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



}
