<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="form">

	<div id="formTitle">
		<spring:message code="label.ajax" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.ajax.sub" />
	</div>

	<br/>
	
	<input type="button" onclick="javascript:ajaxRequest();" value="<spring:message code="label.action.request" />" />
	<input type="button" onclick="javascript:limparAjaxRequest();" value="<spring:message code="label.action.clear" />" />
	
	<div id="response"></div>

</div>