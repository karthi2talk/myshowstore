/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sonata.training.storefront.controllers.form;

/**
 */
public class PointsPaymentDetailsForm
{

	private boolean isPaidByPoints;

	private Integer points;

	/**
	 * @return the isPaidByPoints
	 */
	public boolean isPaidByPoints()
	{
		return isPaidByPoints;
	}

	/**
	 * @param isPaidByPoints
	 *           the isPaidByPoints to set
	 */
	public void setPaidByPoints(final boolean isPaidByPoints)
	{
		this.isPaidByPoints = isPaidByPoints;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints()
	{
		return points;
	}

	/**
	 * @param points
	 *           the points to set
	 */
	public void setPoints(final Integer points)
	{
		this.points = points;
	}









}
