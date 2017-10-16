<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Expires" content="0">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/claro.css" type="text/css">
</head>

<body style="overflow: auto;">
	<center>
		<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" >
			<tr>
				<td class="msg-layout-body" align="left" valign="top"><tiles:insertAttribute name="body" /></td>
			</tr>
		</table>
	</center>
</body>
</html>
