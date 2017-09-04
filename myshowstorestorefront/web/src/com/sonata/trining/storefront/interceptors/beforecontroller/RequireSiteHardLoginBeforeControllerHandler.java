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
package com.sonata.trining.storefront.interceptors.beforecontroller;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeControllerHandler;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.method.HandlerMethod;

import com.sonata.trining.storefront.security.evaluator.impl.RequireHardLoginEvaluator;


/**
 */
public class RequireSiteHardLoginBeforeControllerHandler implements BeforeControllerHandler
{
	private static final Logger LOG = Logger.getLogger(RequireSiteHardLoginBeforeControllerHandler.class);

	public static final String SECURE_GUID_SESSION_KEY = "acceleratorSecureGUID";

	private String loginUrl;
	private String loginAndCheckoutUrl;
	private RedirectStrategy redirectStrategy;
	private RequireHardLoginEvaluator requireHardLoginEvaluator;

	protected String getLoginUrl()
	{
		return loginUrl;
	}

	@Required
	public void setLoginUrl(final String loginUrl)
	{
		this.loginUrl = loginUrl;
	}

	protected RedirectStrategy getRedirectStrategy()
	{
		return redirectStrategy;
	}

	@Required
	public void setRedirectStrategy(final RedirectStrategy redirectStrategy)
	{
		this.redirectStrategy = redirectStrategy;
	}

	public String getLoginAndCheckoutUrl()
	{
		return loginAndCheckoutUrl;
	}

	@Required
	public void setLoginAndCheckoutUrl(final String loginAndCheckoutUrl)
	{
		this.loginAndCheckoutUrl = loginAndCheckoutUrl;
	}

	protected RequireHardLoginEvaluator getRequireHardLoginEvaluator()
	{
		return requireHardLoginEvaluator;
	}

	@Required
	public void setRequireHardLoginEvaluator(final RequireHardLoginEvaluator requireHardLoginEvaluator)
	{
		this.requireHardLoginEvaluator = requireHardLoginEvaluator;
	}

	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws Exception
	{
		// We only care if the request is secure
		if (request.isSecure())
		{
			final boolean isAnonymousUser = requireHardLoginEvaluator.isAnonymousUser();

			if (isAnonymousUser
					&& (!getLoginUrl().contains(request.getServletPath()) || "/logout".contains(request.getServletPath())))
			{
				LOG.warn("No Logged in User found. Redirect to Login Page");
				getRedirectStrategy().sendRedirect(request, response, getRedirectUrl(request));
				return false;
			}
		}
		return true;
	}

	protected String getRedirectUrl(final HttpServletRequest request)
	{
		if (request != null && request.getServletPath().contains("checkout"))
		{
			return getLoginAndCheckoutUrl();
		}
		else
		{
			return getLoginUrl();
		}
	}

	protected <T extends Annotation> T findAnnotation(final HandlerMethod handlerMethod, final Class<T> annotationType)
	{
		// Search for method level annotation
		final T annotation = handlerMethod.getMethodAnnotation(annotationType);
		if (annotation != null)
		{
			return annotation;
		}

		// Search for class level annotation
		return AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), annotationType);
	}
}
