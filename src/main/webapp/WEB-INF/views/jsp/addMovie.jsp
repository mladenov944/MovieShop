
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Flat Sign Up Form Responsive Widget Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href="css/regstyle.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"
	media="all">
<link
	href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Raleway+Dots'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="header-w3l">
		<h1>ADD MOVIE</h1>
	</div>
	<div class="main-agileits">
		<h2 class="sub-head">ADD MOVIE</h2>
		<div class="sub-main">
			<form action="./addmovie" method="post">
			
				<input placeholder="Movie name" name="movie_name" class="name" type="text"
					required=""> <span class="icon1"><i
					class="fa fa-user" aria-hidden="true"></i></span><br> <input
					
					placeholder="Movie Director" name="movie_director" class="name2" type="text"
					required=""> <span class="icon2"><i
					class="fa fa-user" aria-hidden="true"></i></span><br> <input
					
					placeholder="Year" name="movie_year" class="Year" type="text"
					required=""> <span class="icon3"><i
					class="fa fa-envelope" aria-hidden="true"></i></span><br> 
					
						<select name="movie_genre">
						  <option value="">Select a genre:</option>
						  <option value="Action">Action</option>
						  <option value="Horror">Horror</option>
						  <option value="Comedy">Comedy</option>
						  <option value="Fantasy">Fantasy</option>
						  <option value="Drama">Drama</option>
						  <option value="Mystery">Mystery</option>
						  <option value="Animation">Animation</option>
						  <option value="Sci-fy">Sci-fy</option>
						  </select>
					
					<!-- placeholder="Genre" name="movie_genre" class="genre" type="text"
					required=""> <span class="icon4"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br>  -->
					
					<p style="color:red;">INPUT PICTURE <input type="file" name="movie_picture"> </p>
					  
					<input
					placeholder="Price" name="movie_price" class="price" type="text"
					required=""> <span class="icon6"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br> <input
					
					placeholder="IMDB Link" name="movie_link" class="link" type="text"
					required=""> <span class="icon7"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br> <input
					
					placeholder="Quantity" name="movie_quantity" class="quantity" type="text"
					required=""> <span class="icon8"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br> <input
					
					placeholder="Summary" name="movie_summary" class="summary" type="text"
					required=""> <span class="icon9"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br> <input
					
					type="submit" value="ADD">
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="footer-w3"></div>
</body>
</html>