<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Out Page</title>
</head>
<body>
        <div class="checkout_area section_padding_100">
            <div class="container">
                <div class="row">

                    <div class="col-12 col-md-6">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-page-heading">
                                <h5>Please enter the information of your Order</h5>

                            </div>

                            <form action="./checkout" method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="first_name">First Name <span>*</span></label>
                                        <input type="text" class="form-control" name="firstName" value="${sessionScope.firstName}" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name">Last Name <span>*</span></label>
                                        <input type="text" class="form-control" name="lastName" value="${sessionScope.lastName}" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="phone_number">E-mail <span>*</span></label>
                                        <input type="text" class="form-control" name="email"  value="${sessionScope.email}">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="phone_number">Phone No <span>*</span></label>
                                        <input type="text" class="form-control" name="phoneNumber"  value="" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="first_name">City <span>*</span></label>
                                        <input type="text" class="form-control" name="city" value="" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="last_name">Street <span>*</span></label>
                                        <input type="text" class="form-control" name="street" value="" required>
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="first_name">Post Code <span>*</span></label>
                                        <input type="text" class="form-control" name="postCode" value="" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name">Street Number </label>
                                        <input type="text" class="form-control" name="streetNumber" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Block </label>
                                        <input type="text" class="form-control" name="block" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Entrance </label>
                                        <input type="text" class="form-control" name="enterance" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Floor </label>
                                        <input type="text" class="form-control" name="floor" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Apartment </label>
                                        <input type="text" class="form-control" name="apartment" value="" >
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="company">Additional information</label>
                                        <input type="text" class="form-control" name="description" value="">
                                    </div>
                                   
                                   
                                   <button class="btn karl-checkout-btn">Place Order</button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    
                    <div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
                        <div class="order-details-confirmation">

                            <div class="cart-page-heading">
                                <h5>Your Order</h5>
                                <p>The Details</p>
                            </div>

                            <ul class="order-details-form mb-4">
                                <li><span>Number of products</span> <span>${sessionScope.movieItems}</span></li>
                                <li><span>Shipping</span> <span>Free</span></li>
                                <li><span>Total price</span> <span>${sessionScope.totalPrice} lv.</span></li>
                            </ul>
</body>
</html>