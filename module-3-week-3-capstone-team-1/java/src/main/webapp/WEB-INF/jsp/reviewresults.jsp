<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<br>
<c:if test="${results.isEmpty()}">
No reviews yet.
</c:if>

<c:forEach var="parks" items="${results}">
${parks.getParkName()} <br>
${parks.getSurveys()} Total Votes <br>
</c:forEach>

</body>
</html>