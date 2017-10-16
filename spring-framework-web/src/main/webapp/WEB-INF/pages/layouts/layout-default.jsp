<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/claro.js" ></script>

<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapUrl" />
	<link href="${bootstrapUrl}" rel="stylesheet">
	
	<spring:url value="/resources/estilo/jquery-ui-1.8.4.custom.css" var="jquerycustom" />
	<link href="${jquerycustom}" rel="stylesheet">
	
	<spring:url value="/resources/estilo/table.css" var="tablecss" />
	<link href="${tablecss}" rel="stylesheet">
	
	<spring:url value="/resources/estilo/jquery-ui.css" var="jqueryuicss" />
	<link href="${jqueryuicss}" rel="stylesheet">
	
	<spring:url value="/resources/js/jquery-1.6.min.js" var="jqueryUrl" />
	<script src="${jqueryUrl}"></script>
	
	<spring:url value="/resources/js/jquery-ui.js" var="jqueryui" />
	<script src="${jqueryui}"></script>
	
	<spring:url value="/resources/js/jquery.dataTables.min.js" var="jquerydataTablesmin" />
	<script src="${jquerydataTablesmin}"></script>
	
	<spring:url value="/resources/js/jquery.maskedinput-1.3.min.js" var="jquerymaskedinput" />
	<script src="${jquerymaskedinput}"></script>	
	
	<script type="text/javascript">
		$(document).ready(function() {
			// Máscaras
			$('#telefone').mask("(99) 9999-9999?9");
			$('#dtNascimento').mask("99/99/9999");
			
			// Data-table
			oTable = $('#example').dataTable({
				"bPaginate": true,
				"bJQueryUI": false,
				"bDestroy": true,
				"sPaginationType": "full_numbers"
			});
			
			// Abas
			$('#tabs').tabs();
		});
	</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/claro.css" type="text/css">
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
			<div id="navSection">
				<tiles:insertAttribute name="menu" />
			</div>
			<div id="bodySection">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<footer id="pageFooter">
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>

<c:if test="ERROR_FLAG">
	<!-- Apresentação de Janela PopUp com Erros -->
	<script>
  function showError (uri,height,width) {
    var top = (screen.height-height)/2;
    var left = (screen.width-width)/2;
    var win = window.open("<%=request.getContextPath()%>
		" + uri,
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

		var h = 300;
		var w = 450;
		showError("/error01.jsp", h, w);
	</script>
</c:if>