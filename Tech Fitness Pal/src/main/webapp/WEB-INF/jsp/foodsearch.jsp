<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="formAction" value="/foodsearch" />
<html>

<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Nutrition Calculator</h1>
<form method="POST" action = "${formAction}">
 <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />


  Enter meal name:<input type="text" name="mealLabel" id="mealLabel" placeholder="Lunch" />  <br>
  Enter foods eaten:<input type="text" name="foodEaten" id="foodEaten" placeholder="Food" /> <br>
  Enter total calories:<input type="text" name="calories" id="calories" placeholder="600" /> <br>
  Enter date it was eaten:<input type="text" name="date" id="date" placeholder="YYYY/MM/DD" /> <br>
  
 
<button type="submit" name="saveMeal" value="saveMeal">Submit that goodness</button>
</form>
</body>
</html>