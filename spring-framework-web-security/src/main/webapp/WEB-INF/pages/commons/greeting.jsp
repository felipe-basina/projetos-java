<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr class="wellcome">
    <td valign=top><img src="<%=request.getContextPath()%>/images/chip-cut.gif"></td>
    <td align="left"  width="100%"><spring:message code="msg.wellcome"/> <%= request.getRemoteUser() %> </td>
    <td align="right">[<cutil:instance/>]</td>
  </tr>
</table>

