<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Reservations</title>

<style type="text/css">

.err-message {
	color: red;
	border: 1px solid red;
	width: 50%;
	padding: 10px;
	font-weight: bold;
}

.succ-message {
	color: green;
	border: 1px solid green;
	width: 50%;
	padding: 10px;
	font-weight: bold;
}

</style>

</head>
<body>

	<%@include file="../commons/message.jsp" %>

	<c:if test="${not empty reservations}">
		<br/><c:forEach items="${reservations}" var="reservation">
	   		Reservation for <c:out value="${reservation.name}" /> - <a href='<c:url value='/reservation/delete/${reservation.id}'/>'>Remove?</a><br/>
		</c:forEach>
	</c:if>
		
	<br/><input type="button" value="form" onclick="javascript:formHandler();" />
	
	<br/><form action='<c:url value='/reservation/save' />' method="post" id="formId" style="display: none;">
		<br/>Enter your name for a reservation:
		<input type="text" name="name" />
		<input type="submit" value="submit" />
	</form>
	
</body>
</html>
<script>

function formHandler() {
	var formCurrentStyle = document.getElementById('formId').style.display;
	console.log('formCurrentStyle: ' + formCurrentStyle);
	
	if (formCurrentStyle === 'none') {
		document.getElementById('formId').style.display = 'block';
	} else {
		document.getElementById('formId').style.display = 'none';
	}
}

</script>