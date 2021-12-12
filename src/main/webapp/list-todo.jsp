<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Web Student Tracker</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- ${TODO_LIST}-->
<div id="wrapper">
<div id="header">
<h2>ESILV Engineer School</h2>
</div>
</div>
<div id="container">
<div id="content">
<table>
<tr>
<th>id </th>
<th>description</th>
</tr>
<c:forEach var="tempTodo" items="${TODO_LIST }" >
<tr>
<td> ${tempTodo.getId}</td>
<td> ${tempTodo.getDescription}</td>

</c:forEach>
</table>
</div>
</div>
</body>
</html>