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
			<form action="http://localhost:5553/WebProject/MainServlet" method="get">
				<button  id="logout" type="submit">Logout</button>
			</form>
		</div>	
	    <form action="http://localhost:5553/WebProject/TodoControllerServlet" method="post" style = "position:relative; left:0px; top:30px;">
	        <input name="deleteNumber" size="12" placeholder="Todo number">
	        <input type="submit" value="Delete" style = "position:relative; left:4px;">
	    </form>    	    
   	    <form action="http://localhost:5553/WebProject/TodoControllerServlet" method="post" style = "position:relative; left:0px; top:30px;">
	        <input id="newTodoText" name="newTodoText" type="text" placeholder="Todo Text">
	        <input type="submit" value="Ajouter" style = "position:relative; left:4px;">
	    </form>     
	</div>
</body>
</html>