<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Web Student Tracker</title>
<link type="text/css" rel="stylesheet" href="css/Todo.css">
</head>
<body>
	<!-- ${TODO_LIST}-->
	<div class="red_band">
		<div>
			<h1 class="welcome">Welcome ${sessionScope.user }</h1>
	    	<h4 class="maintext">List of things to do</h4>
		</div>
	    <img id="logo" src="esilv_250.jpg" alt="Esilv_logo" width="80" height="80">
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>id</th>
					<th>description</th>
				</tr>
				<c:forEach var="tempTodo" items="${TODO_LIST }">
					<tr>
						<td>${tempTodo.getId()}</td>
						<td>${tempTodo.getDescription()}</td>
				</c:forEach>
			</table>
			<form action="http://localhost:9003/WebProject/MainServlet" method="get">
				<button  id="logout" type="submit">Logout</button>
			</form>
		</div>	   
	</div>
</body>
</html>