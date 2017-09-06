/**
 *
 */
package com.sonata.training.storefront.controllers.form.validation;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import javax.annotation.Resource;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sonata.training.storefront.controllers.form.PointsPaymentDetailsForm;


/**
 * @author karthi.m
 *
 */

public class PointsPaymentValidator implements Validator
{

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Override
	public boolean supports(final Class<?> form)
	{
		return PointsPaymentDetailsForm.class.equals(form);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final PointsPaymentDetailsForm form = (PointsPaymentDetailsForm) object;
		final CustomerData customer = customerFacade.getCurrentCustomer();

		if (form.getPoints() > customer.getLoyalityPoints())
		{
			errors.rejectValue("points", "payment.points.invalid");
		}


	}



}
