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
        <li><button onclick="sortListDir()">SORT BY TITLE</button></li>
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
        
     <div><ul id="${movies}">   
      <c:forEach items="${movies}" var="movie" varStatus="loop">
          
          <span class="name"><a id = "${movie.name}" href="/FinalProject/${movie.id}"><img src='${movie.picture}' alt="" height = "200px" width="150px"/></a></span>
      </c:forEach>
	</div>
	<!--  sorted films by name asc and desc -->
		
		
		<script>
		
		function sortListDir() {
		  var list, i, switching, b, shouldSwitch, dir, switchcount = 0;
		  list = document.getElementById("${movies}");
		  switching = true;
		  dir = "asc"; 
		  while (switching) {
		    switching = false;
		    b = list.getElementsByTagName("SPAN");
		    for (i = 0; i < (b.length - 1); i++) {
		      shouldSwitch = false;
		      if (dir == "asc") {
		        if (b[i].innerHTML.toLowerCase() > b[i + 1].innerHTML.toLowerCase()) {
		          shouldSwitch= true;
		          break;
		        }
		      } else if (dir == "desc") {
		        if (b[i].innerHTML.toLowerCase() < b[i + 1].innerHTML.toLowerCase()) {
		          shouldSwitch= true;
		          break;
		        }
		      }
		    }
		    if (shouldSwitch) {
		      b[i].parentNode.insertBefore(b[i + 1], b[i]);
		      switching = true;
		      switchcount ++;
		    } else {
		      if (switchcount == 0 && dir == "asc") {
		        dir = "desc";
		        switching = true;
		      }
		    }
		  }
		}
		</script>
	
     </ul>   
  </div>
  </div>
</div> 
</body>
</html>

