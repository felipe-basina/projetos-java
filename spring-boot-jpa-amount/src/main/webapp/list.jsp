<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>... list ...</title>

<link type="text/css" href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet"></link>
<link type="text/css" href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(function() {
		console.log('ok');
		
		$('#list-table').DataTable({
			pagingType: 'simple' // Remove a paginação por números das páginas
		});
	});
</script>

</head>
<body>

<div id="list_person">

	<c:choose>
		<c:when test="${! empty errorMsg}">
			<span>${errorMsg}</span>
		</c:when>
		<c:otherwise>
			<table id="list-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
			<!-- 				<th>MANY DESCRIPTION</th> -->
			<!-- 				<th>MANY NUMBER</th> -->
						<th>ONE DESCRIPTION</th>
						<th>ONE FUTURE DATE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${persons}" var="person" varStatus="loop">
						<tr style="${person.id % 2 == 0 ? 'background-color: cyan' : 'background-color: yellow'}" class="alinhamento-texto">
							<td>${person.id}</td>
							<td>${person.name}</td>
			<%-- 						<td>${}</td> --%>
			<!-- 						<td></td> -->
							<td>${person.one.description}</td>
							<td>
								<fmt:formatDate value="${person.one.futureDate}" pattern="dd/MM/yyyy HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th></th>
						<th></th>
			<!-- 					<th></th> -->
			<!-- 					<th></th> -->
						<th></th>
						<th></th>
					</tr>
				</tfoot>
			</table>		
		</c:otherwise>
	</c:choose>

</div>

</body>
</html>