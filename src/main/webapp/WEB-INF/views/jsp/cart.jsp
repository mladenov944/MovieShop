<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
</head>
<body>

<div class="cart_section">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="cart_container">
						<div class="cart_title">Shopping Cart</div>
						<div class="cart_items">
							<ul class="cart_list">
							<c:choose> 
									<c:when	test = "${fn:length(movies)==0}">
										<div class="order_total_amount">Your cart is empty</div>
									</c:when>
									<c:otherwise>
										<c:forEach items="${movies}" var="item" varStatus="loop">
								<li class="cart_item clearfix">
									
									<div class="cart_item_image"><a href="./${movies.id}"><img src="${movies.picture}" alt=""></a></div>
									<div class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<div class="cart_item_name cart_info_col">
											<div class="cart_item_title">Name</div>
											<div class="cart_item_text">${movies.name}</div>
										</div>
										<div class="cart_item_quantity cart_info_col">
											<div class="cart_item_title">Quantity</div>
											<div class="cart_item_text">${movies.quantity}</div>
										</div>
										<div class="cart_item_price cart_info_col">
											<div class="cart_item_title">Price</div>
											<div class="cart_item_text">${movies.price} лв.</div>
										</div>
										<div class="cart_item_price cart_info_col">
										<div class="cart_item_title">
											<button type="button" onclick="window.location.href='/FinalProject/removeItem?itemId=' + '${movies.id}';" class="button cart_button_clear">X</button>
										</div>
										</div>
									<!--  	<div class="cart_item_total cart_info_col">
											<div class="cart_item_title">Total</div>
											<div class="cart_item_text"></div>
										</div> -->
									</div>
									
								</li>
							</c:forEach>
									</c:otherwise>
								</c:choose>
							
								
								
							</ul>
						</div>
						
						<!-- Order Total -->
						<div class="order_total">
							<div class="order_total_content text-md-right">
								<div class="order_total_title">Order Total:</div>
								<div class="order_total_amount">${totalPrice}</div>
							</div>
						</div>

						<div class="cart_buttons">
							<button type="button" onclick="window.location.href='/FinalProject/index';" class="button cart_button_clear">Shop more</button>
							<button type="button" onclick="window.location.href='/FinalProject/checkout';" class="button cart_button_checkout">Order</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>