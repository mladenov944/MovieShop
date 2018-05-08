<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<title>Admin Page</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-func.js"></script>
<!--[if IE 6]><link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" /><![endif]-->
</head>

<body>
	<div id="shell">
	<h4>Welcome, Admin ! </h4>
  <div id="header">
    <div class="social"> <span>FOLLOW US ON:</span>
      <ul>
        <li><a class="facebook" href="https://www.facebook.com">facebook</a></li>
      </ul>
    </div>
    <div id="navigation">
      <ul>
        <li><a class="active" href="/FinalProject/adminPage">HOME</a></li>
        <li><a href="/FinalProject/addMovie">ADD MOVIE</a></li>
        <li><a href="/FinalProject/logout">LOGOUT</a></li>
      </ul>
    </div>
    <div id="sub-navigation">
    </div>
  </div>
    <div id="content">
      <div class="box">
     <ul>   
      <c:forEach items="${movies}" var="movie" varStatus="loop">
          
          <span class="name"><a href="./movieDetails/${movie.id}"><img src='${movie.picture}' alt="" height = "200px" width="150px"/></a></span>
      </c:forEach>
     </ul>   
  </div>
  </div>
</div> 
</body>
</html>

