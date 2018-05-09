<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  
  <link href="/FinalProject/css/theme.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/FinalProject/css/cartstyle.css" rel="stylesheet">  

<link href="/FinalProject/scss/style.scss" rel="stylesheet">
<title>Shopping Cart</title>
</head>
<body>

<h1>Shopping Cart</h1>

<div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">Image</label>
    <label class="product-details">Product</label>
    <label class="product-price">Price</label>
    <label class="product-quantity">Quantity</label>
    <label class="product-removal">Remove</label>
    <label class="product-line-price">Total</label>
  </div>
	
		  <div class="product">
		    <div class="product-image">
		      <img src="${movie.picture}" height = "150px" width="100px">
		    </div>
		    <div class="product-details">
		      <div class="product-title">${movie.name}</div>
		      <p class="product-description">${movie.summary}</p>
		    </div>
		    <div class="product-price">${movie.price}</div>
		    <div class="product-quantity">
		      <input type="number" value="1" min="1" max="5">
		    </div>
		    <div class="product-removal">
		      <button class="remove-product">
		        Remove
		      </button>
		    </div>
		    <div class="product-line-price">${movie.price}</div>
		  </div>
  

  <div class="totals">
    <div class="totals-item totals-item-total">
      <label>Total Price</label>
      <div class="totals-value" id="cart-total">${movie.price}</div>
    </div>
  </div>
      
      <button class="checkout">Checkout</button>

</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="/FinalProject/js/index.js"></script>
</body>
</html>