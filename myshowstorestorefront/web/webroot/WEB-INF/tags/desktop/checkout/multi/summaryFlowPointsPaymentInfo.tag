<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="summaryDeliveryAddress">
	<ycommerce:testId code="checkout_deliveryAddressData_text">
		
			<strong>Loyalty Points</strong>
			<ul>
				<li><spring:theme code="basket.page.points.paid" htmlEscape="false"/>&nbsp;&nbsp;  ${fn:escapeXml(cart.loyaltyPoints)}</li>
			</ul>
		
	</ycommerce:testId>

		<ycommerce:testId code="checkout_changeAddress_element">
		    <c:url value="/checkout/multi/points-payment/add" var="editPointsUrl" />
			<a href="${editPointsUrl}" class="button positive editButton"><spring:theme code="checkout.summary.edit"/></a>
		</ycommerce:testId>
</div>