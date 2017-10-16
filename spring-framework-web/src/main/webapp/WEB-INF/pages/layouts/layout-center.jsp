<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<script src="<%=request.getContextPath()%>/js/claro.js" type="text/javascript"></script>

	<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapUrl" />
	<link href="${bootstrapUrl}" rel="stylesheet">
	
	<spring:url value="/resources/estilo/jquery-ui-1.8.4.custom.css" var="jquerycustom" />
	<link href="${jquerycustom}" rel="stylesheet">
	
	<spring:url value="/resources/estilo/table.css" var="tablecss" />
	<link href="${tablecss}" rel="stylesheet">
	
	<spring:url value="/resources/js/jquery.js" var="jqueryUrl" />
	<script src="${jqueryUrl}"></script>
	
	<spring:url value="/resources/js/jquery.mim.js" var="jquerymim" />
	<script src="${jquerymim}"></script>
	
	<spring:url value="/resources/js/jquery.dataTables.min.js" var="jquerydataTablesmin" />
	<script src="${jquerydataTablesmin}"></script>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/claro.css" type="text/css" />
</head>
<script type="text/javascript">
function doOnUnLoad() { 
  if (window.screenTop>10000 && window.screenLeft>10000) {
    var height = 300;
    var width = 450;
    var top = (screen.height-height)/2;
    var left = (screen.width-width)/2;
    var win = window.open("<%=request.getContextPath()%>/invalidate.jsp",
							"_new",
							"height="
									+ height
									+ ",width="
									+ width
									+ ",top="
									+ top
									+ ",left="
									+ left
									+ ",menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=yes,toolbar=no");
			win.focus();
		}
	}
	window.onunload = doOnUnLoad;
</script>
<body>
	<div id="pageContainer">
		<header id="pageHeader">
			<tiles:insertAttribute name="header" />
		</header>
		<div id="pageBody">
			<tiles:insertAttribute name="body" />
		</div>
		<footer id="pageFooter">
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>
