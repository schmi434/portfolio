<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:import url="/WEB-INF/jsp/header.jsp" />


<img id="detailimg" src="img/parks/${fn:toLowerCase(park.getParkCode())}.jpg" /> <br>
<p id="detailtitle">${park.getParkName()} - ${park.getState()} - ${park.getParkCode()}</p>
<p id="detailp">${park.getAcreage()} acres. ${park.getElevationInFeet()} feet above sea level. ${park.getMilesOfTrail()} miles of trail. 
${park.getNumberOfCampsites()} campsites. ${park.getClimate()} climate. Founded in ${park.getYearFounded()}. 
${park.getAnnualVisitorCount()} visitors per year. <br>
"${park.getInspirationalQuote()}" -${park.getQuoteSource()} <br>
${park.getParkDescription()} <br>
$${park.getEntryFee()} to enter.<br>
${park.getNumSpecies()} different animal species. <br>
</p>

<div id="detailtemp">
<c:set var = "scale" scope = "session" value = "Farenheit"/> <!-- the session doesnt actually work -->

<form method=POST>
<input type="radio" name="scale" value="Farenheit"> Farenheit <br>
<input type="radio" name="scale" value="Celsius"> Celsius<br>
<input type="submit" value="Change">
</form>


<c:forEach var="day" items="${forecasts}">
<img src="${day.getImgUrl()}" /> <br>

${day.getForecast()} <br>

<c:if test = "${scale == 'Celsius'}"> 
<c:set var="hightemp" value="${day.getHighTemp()}"/>
<c:set var="hightemp" value="${hightemp - 32.0}"/>
<c:set var="hightemp" value="${hightemp * (5.0 / 9.0)}"/>

<fmt:formatNumber type="number" maxFractionDigits = "0" value="${hightemp}" var="hightemp"/>

<c:set var="lowtemp" value="${day.getLowTemp()}"/>
<c:set var="lowtemp" value="${lowtemp - 32.0}"/>
<c:set var="lowtemp" value="${lowtemp * (5.0/9.0)}"/>

<fmt:formatNumber type="number" maxFractionDigits = "0" value="${lowtemp}" var="lowtemp"/>

High of: ${hightemp} C<br>
Low of: ${lowtemp} C<br>
</c:if>

<c:if test = "${scale == 'Farenheit'}">
<c:set var="hightemp" value="${day.getHighTemp()}"/>
<c:set var="lowtemp" value="${day.getLowTemp()}"/>
<fmt:formatNumber type="number" maxFractionDigits = "0" value="${hightemp}" var="hightemp"/>
<fmt:formatNumber type="number" maxFractionDigits = "0" value="${lowtemp}" var="lowtemp"/>

High of: ${hightemp} F<br>
Low of: ${lowtemp} F<br>
</c:if>

<!-- so if the day is 1, we want to give weather recommendations
we need one for weather and one for temp -->

<c:if test="${day.getForecastDay() == 1 }">

<c:choose> 
<c:when test = "${day.getForecast().equals('snow')}">
<p>Be sure to pack snowshoes</p>
</c:when>
<c:when test = "${day.getForecast().equals('rain')}">
<p>Be sure to pack raingear and wear waterproof shoes</p>
</c:when>
<c:when test = "${day.getForecast().equals('thunderstorms')}">
<p>Be prepared to seek shelter and avoid hiking on exposed ridges</p>
</c:when>
<c:when test = "${day.getForecast().equals('sunny')}">
<p>Be sure to pack sunblock</p>
</c:when>
</c:choose>

<c:choose>
<c:when test = "${day.getHighTemp() > 75}">
<p>Bring an extra gallon of water</p>
</c:when>
<c:when test = "${day.getHighTemp() - day.getLowTemp() > 20}">
<p>Wear breathable layers</p>
</c:when>
<c:when test = "${day.getLowTemp() < 20}">
<p>Beware of exposure to frigid temperatures</p>
</c:when>   

</c:choose>

</c:if>

</c:forEach>

</div>

</body>
</html>