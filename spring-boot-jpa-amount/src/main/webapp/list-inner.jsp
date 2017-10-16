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
<link type="text/css" href="${pageContext.request.contextPath}/css/jquery.jscrollpane.css" rel="stylesheet" media="all" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jscrollpane.min.js"></script>

<script type="text/javascript">

	function fnFormatDetails(val) {
		var sOut = '<div class="scroll-pane"><table cellpadding="5" cellspacing="5" border="0" style="padding-left:50px;">' 
			+ val 
			+ '</table></div>';		
//console.log('sOut:' + sOut);
		return sOut;
	}
	
	$(function() {
		console.log('ok');
		
		var oTable = $('#list-table').dataTable({
			pagingType: 'simple', // Remove a paginação por números das páginas
			aoColumnDefs: [ // Remove o item para ordenação do último elemento
				  {
					 bSortable: false,
					 aTargets: [ -1 ]
				  }
			]
		});
		
		$('#list-table tbody').on('click', '.selecionado', function(e) {
			e.preventDefault();
			
			var signal = $(this).html();
	
			var image_ = '<img src="./images/:signal.png" style="cursor: pointer;" title=":msg manies">';
			
			var table_ = $(this).closest('.alinhamento-texto').find('div table')
							.html()
							.replace(/\n/g, "")
					    	.replace(/[\t ]+\</g, "<")
					    	.replace(/\>[\t ]+\</g, "><")
					    	.replace(/\>[\t ]+$/g, ">");
//console.log('table_: ' + table_);
			
			if (signal.indexOf('plus') > 0) {
				image_ = image_.replace(':signal', 'minus');
				image_ = image_.replace(':msg', 'Hide');
				oTable.fnOpen(this, fnFormatDetails(table_), "line-row");
				$(this).html(image_);
				$('.scroll-pane').jScrollPane();
			 } else {
				image_ = image_.replace(':signal', 'plus');
				image_ = image_.replace(':msg', 'Show');
				$(this).html(image_);
				oTable.fnClose(this);
			 }            
	        
		});
		
	});
	
	// Executa depois do $.ready
	// Referência: http://stackoverflow.com/questions/3008696/after-all-document-ready-have-run-is-there-an-event-for-that
	$(window).load(function() {
		console.log('load depois do DOM!');
		$('#espera').hide();
		$('#list-table').show();
	});

</script>

</head>
<body>

<div id="list_person">

	<div id="espera" style="display: inline;">
		<h3>Por favor, aguarde...</h3>
		<img alt="Aguarde..." src="./images/ajax-loader.gif">
	</div>

	<c:choose>
		<c:when test="${! empty errorMsg}">
			<span>${errorMsg}</span>
		</c:when>
		<c:otherwise>
			<table id="list-table" style="display: none;">
				<thead>
					<tr>
						<th>#</th>
						<th>ID</th>
						<th>NAME</th>
						<th>CREATION DATE</th>
						<th>STATUS</th>
						<th>ONE DESCRIPTION</th>
						<th>ONE FUTURE DATE</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${persons}" var="person" varStatus="loop">
						<tr style="${person.id % 2 == 0 ? 'background-color: cyan' : 'background-color: yellow'}" class="alinhamento-texto">
							<td class="selecionado">
								<img src="${pageContext.request.contextPath}/images/plus.png" style="cursor: pointer;" title="Show manies">
							</td>
							<td>${person.id}</td>
							<td>${person.name}</td>
							<td>
								<fmt:formatDate value="${person.creationDate}" pattern="dd/MM/yyyy HH:mm:ss" />
							</td>
							<td>${person.active}</td>
							<td>${person.one.description}</td>
							<td>
								<fmt:formatDate value="${person.one.futureDate}" pattern="dd/MM/yyyy HH:mm:ss" />
							</td>
							<td>
								<div id="div-${loop.index}">
									<table id="detailsTable-${loop.index}" style="display: none;">
										<tr>
											<th>#</th>
											<th>MANY ID</th>
											<th>MANY DESCRIPTION</th>
											<th>MANY NUMBER</th>
										</tr>
										<c:forEach items="${person.manies}" var="many" varStatus="loopy">
											<tr>
												<td>${loopy.index}</td>
												<td>${many.id}</td>
												<td>${many.description}</td>
												<td>${many.number}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
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