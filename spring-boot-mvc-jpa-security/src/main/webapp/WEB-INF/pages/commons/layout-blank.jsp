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
<body>
	<div id="pageContainer">
		<div id="pageBody">
			<div id="bodySection">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
</body>
</html>