<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<br>

<form:form action="/m3-java-capstone/reviewresults" method="post" modelAttribute="register">
Enter your favorite Park <select name="parkCode" required>
<option disabled selected value=""></option>
<c:forEach var="park" items="${parks}">
<option value="${park.getParkCode()}">${park.getParkName()}</option>
</c:forEach>
</select> <br>
<%-- <div>
<form:label path="email" for="email">Enter your email</form:label> 
<form:input path="email"/>
<form:errors path="email" cssClass="error"/> --%>
Enter your email <input type="text" name="email" value="" required"> <br>
<!-- </div> -->
Enter your state <select name="state" required>	
<option disabled selected value=""></option>
<option value="alabama">Alabama</option>
<option value="alaska">Alaska</option>
<option value="arizona">Arizona</option>	
<option value="arkansas">Arkansas</option>
<option value="california">California</option>
<option value="colorado">Colorado</option>
<option value="connecticut">Connecticut</option>
<option value="delaware">Delaware</option>
<option value="florida">Florida</option>
<option value="georgia">Georgia</option>
<option value="hawaii">Hawaii</option>
<option value="idaho">Idaho</option>
<option value="illinois">Illinois</option>
<option value="indiana">Indiana</option>
<option value="iowa">Iowa</option>
<option value="kansas">Kansas</option>
<option value="kentucky">Kentucky</option>
<option value="lousiana">Lousiana</option>
<option value="maine">Maine</option>
<option value="maryland">Maryland</option>
<option value="massachusetts">Massachusetts</option>
<option value="michigan">Michigan</option>
<option value="minnesota">Minnesota</option>
<option value="mississippi">Mississippi</option>
<option value="missouri">Missouri</option>
<option value="montana">Montana</option>
<option value="nebraska">Nebraska</option>
<option value="nevada">Nevada</option>
<option value="newhampshire">New Hampshire</option>
<option value="newjersey">New Jersey</option>
<option value="newmexico">New Mexico</option>
<option value="newyork">New York</option>
<option value="northcarolina">North Carolina</option>
<option value="northdakota">North Dakota</option>
<option value="ohio">Ohio</option>
<option value="oklahoma">Oklahoma</option>
<option value="oregon">Oregon</option>
<option value="pennsylvania">Pennsylvania</option>
<option value="rhodeisland">Rhode Island</option>
<option value="southcarolina">South Carolina</option>
<option value="southdakota">South Dakota</option>
<option value="tennessee">Tennessee</option>
<option value="texas">Texas</option>
<option value="utah">Utah</option>
<option value="vermont">Vermont</option>
<option value="virgina">Viginia</option>
<option value="washington">Washington</option>
<option value="westvirgina">West Virgina</option>
<option value="wisconsin">Wisconsin</option>
<option value="wyoming">Wyoming</option>
</select> <br>


Enter your activity level <select name="activity" required>
<option disabled selected value=""></option>
<option value="inactive">Inactive</option>
<option value="sedentary">Sedentary</option>
<option value="active">Active</option>
<option value="extremelyactive">Extremely Active</option>
</select> <br>
<input type="submit" value="submit">
</form:form>
<br>
<br>
<br>
</body>
</html>