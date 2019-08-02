<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<br>
<c:forEach var="park" items="${parks}">
	<c:url var="parkDetailUrl" value="/detail">
		<c:param name="code" value="${park.getParkCode().toLowerCase()}" />
	</c:url>
	<div id="home">
		<a class="product-image" href="${parkDetailUrl}"> 
		<img src="img/parks/${fn:toLowerCase(park.getParkCode())}.jpg" />
		</a>
		<p id="park">${park.getParkName()}</p>
		<p id="state">${park.getState()}</p>
		<p>${park.getParkDescription()}</p>


	</div>
</c:forEach>

</body>
</html>