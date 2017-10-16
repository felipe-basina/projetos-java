<c:if test="${not empty errrMessage}">
	<div class="err-message">
		<c:out value="${errrMessage}" />
	</div>
</c:if>
<c:if test="${not empty succMessage}">
	<div class="succ-message">
		<c:out value="${succMessage}" />
	</div>
</c:if>