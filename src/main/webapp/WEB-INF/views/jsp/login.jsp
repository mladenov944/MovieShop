<!DOCTYPE html>
<%@page import="java.net.URLEncoder" %>
<%
    String fbURL = "http://www.facebook.com/dialog/oauth?client_id=myfacebookappid&redirect_uri=" + URLEncoder.encode("http://myappengineappid.appspot.com/signin_fb.do") + "&scope=email";
%>
<html lang="en">
<head>
<style>
body {
	background-image: url('images/bg.jpg');
}
</style>

<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/regstyle.css" rel="stylesheet" type="text/css"
	media="all">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>

<body>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '{2194336664121821}',
      cookie     : true,
      xfbml      : true,
      version    : '{latest-api-version}'
    });
      
    FB.AppEvents.logPageView();   
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
	});
  

  {
      status: 'connected',
      authResponse: {
          accessToken: '...',
          expiresIn:'...',
          signedRequest:'...',
          userID:'...'
      }
  }
</script>


	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" action="./login"
					method="post">
					<span class="login100-form-title p-b-33"> Account Login </span>

					<div class="wrap-input100 validate-input"
						data-validate="Valid email is required: ex@abv.bg">
						<input class="input100" type="text" name="email"
							placeholder="Email"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
					

					<div class="wrap-input100 rs1 validate-input"
						data-validate="Password is required">
						<input class="input100" type="password" name="pass"
							placeholder="Password"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn">Sign in</button>
					</div>

					<div class="text-center p-t-45 p-b-4">
						<span class="txt1"> Forgot </span> <a href="https://media.giphy.com/media/xUySTQrznWtO9pWxVe/giphy.gif" class="txt2 hov1">
							Username / Password? </a>
					</div>

					<div class="text-center">
						<span class="txt1"> Create an account? </span> <a
							href="./register" class="txt2 hov1"> Sign up </a>
					</div>
					<div class="text-center">
						<span class="txt1"> </span>
							<a href="/FinalProject/fblogin" class="txt2 hov1">Log in with FB </a>
							</div>
							
				</form>
				
			</div>
		</div>
	</div>



	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>