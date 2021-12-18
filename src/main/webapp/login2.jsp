<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Screen</title>
<meta name="author" content="Herve-Henri"/>
<link type="text/css" rel="stylesheet" href="css/Login.css">
</head>
<body>
	<div class="red_band">
	    <h4 class="maintext">Please enter your login credentials</h4>
	    <img id="logo" src="esilv_250.jpg" alt="Esilv_logo" width="80" height="80">
	</div>
    <div id="wrapper">
		<form action="http://localhost:5553/WebProject/MainServlet" method="post">
			<div class="container1">
				<label for="username">Username</label> 
				<input id="username" name="Username" type="text">
			</div>
			<div class="container2">
				<label for="password">Password</label> 
				<input id="password" name="Password" type="password">
			</div>
			<div class="container3">
				<input type="submit" id="confirm" value="Confirm"></input>
			</div>
		</form>
		<div class="container4">
			<p>Wrong credentials, ${sessionScope.Tries} tries remaining.</p>
		</div>
	</div>
</body>
</html>