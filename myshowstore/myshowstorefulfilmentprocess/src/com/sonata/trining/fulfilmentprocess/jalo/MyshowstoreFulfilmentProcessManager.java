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
package com.sonata.trining.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.sonata.trining.fulfilmentprocess.constants.MyshowstoreFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class MyshowstoreFulfilmentProcessManager extends GeneratedMyshowstoreFulfilmentProcessManager
{
	public static final MyshowstoreFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (MyshowstoreFulfilmentProcessManager) em.getExtension(MyshowstoreFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
