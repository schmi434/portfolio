<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:url var="formAction" value="/profile" />
<html>
<head>  
<meta charset="ISO-8859-1">
<title>Tell us about yourself and your goals</title>
</head>
<body>

	<h1>Tell us about yourself and your goals!</h1>
	<form method="POST" action = "${formAction}">
	
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" /> <%-- Given by Andy to resolve csrf filter problem.--%>

Display Name:<input  type="text" name="displayName" id="displayName" /><br>
Starting Weight:<input  type="text" name="startingWeight" id="startingWeight" /><br>
Goal Weight:<input  type="text" name="goalWeight" id = "goalWeight" /><br>
Age:<input  type="text" name="age" id ="age"/><br>
Height:<input  type="text" name="height" id="height" /><br>
<input type="submit" value="Submit" />
</form>
</body>
</html>