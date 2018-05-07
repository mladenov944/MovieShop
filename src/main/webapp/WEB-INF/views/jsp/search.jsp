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
        <li><a href="/FinalProject/searchAll">SHOW BY TITLE</a></li>
        <li><a href="#">SHOW BY GENRE</a></li>
       
        <li><select name="movie_genre">
						  <option value="">Select a genre:</option>
						  <option value="Action">Action</option>
						  <option value="Horror">Horror</option>
						  <option value="Comedy">Comedy</option>
						  <option value="Fantasy">Fantasy</option>
						  <option value="Drama">Drama</option>
						  <option value="Mystery">Mystery</option>
						  <option value="Animation">Animation</option>
						  <option value="Sci-fy">Sci-fy</option>
						  </select></li>
      </ul>
      <div id="search">
        <form action="#" method="get" accept-charset="utf-8">
          <label for="search-field">SEARCH</label>
          <input type="text" name="search field" value="Enter search here" id="search-field" class="blink search-field"  />
          <input type="submit" value="GO!" class="search-button" />
        </form>
      </div>
    </div>
  </div>
    <div id="content">
      <div class="box">
        <div class="head">
          <h2>LATEST TRAILERS</h2>
          <p class="text-right"><a href="#">See all</a></p>
        </div>
     <ul>   
      <c:forEach items="${movies}" var="movie" varStatus="loop">
          
          <span class="name"><a href="./movieDetails/${loop.index}"><img src='${movie.picture}' alt="" height = "200px" width="150px"/></a></span>
      </c:forEach>
     </ul>   
  </div>
  </div>
</div> 
</body>
</html>

