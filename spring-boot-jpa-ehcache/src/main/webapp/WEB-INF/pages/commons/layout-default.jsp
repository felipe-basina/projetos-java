<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estilo.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-mask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script-geral.js"></script>
<body>
	<div id="pageContainer">
		<header id="pageHeader">
			<tiles:insertAttribute name="header" />
		</header>
		<hr>
		<div id="pageBody">
			<div id="bodySection">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<br><br>
		<hr>
		<footer id="pageFooter">
			<tiles:insertAttribute name="footer" />
		</footer>
		<hr>
	</div>
</body>
</html>