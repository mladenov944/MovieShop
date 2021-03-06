<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<title>Home Page</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-func.js"></script>
<!--[if IE 6]><link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" /><![endif]-->
</head>

<body>
	<div id="shell">
	<h4>Welcome, <%= session.getAttribute("name") %> <%= session.getAttribute("lastName") %> </h4>
	<h4>Your balance is:  <%= session.getAttribute("cash") %> </h4>
	
	<form method = "POST" action="./subscribe" >
						<input type="hidden" name="id" value = "${user.id}">
						<input class="button" type="submit" value = "subscribe">
						
						</form>
						<form method = "POST" action="./unsubscribe" >
						<input type="hidden" name="id" value = "${user.id}">
						<input class="button" type="submit" value = "Cancel subscription">
						</form>
  <div id="header">
    <div class="social"> <span>FOLLOW US ON:</span>
      <ul>
      
        <li><a class="facebook" href="https://www.facebook.com">facebook</a></li>
      </ul>
    </div>
    <div id="navigation">
      <ul>
        <li><a class="active" href="/FinalProject/loggedInHome">HOME</a></li>
        <li><a href="/FinalProject/changePassword">CHANGE PASSWORD</a></li>
        <li><a href="/FinalProject/logout">LOGOUT</a></li>
      </ul>
    </div>
    <div id="sub-navigation">
      <ul>
        <li><a href="/FinalProject/sortedMovies">SORT BY TITLE</a></li>
       
      </ul>
      <div id="search">
      </div>
    </div>
  </div>
    <div id="content">
      <div class="box">
        <div class="head">
          <h2>LATEST TRAILERS</h2>
          <p class="text-right"><a href="#">See all</a></p>
        </div>
        
     <div><ul id="${movies}"></ul>   
      <c:forEach items="${movies}" var="movie" varStatus="loop">
          
          <span class="name"><a id = "${movie.name}" href="/FinalProject/${movie.id}"><img src='${movie.picture}' alt="" height = "200px" width="150px"/></a></span>
      </c:forEach>
	</div>
        
  </div>
  </div>
</div> 
</body>
</html>

