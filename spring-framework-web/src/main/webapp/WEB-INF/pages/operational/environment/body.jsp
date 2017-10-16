<%@ page language="java" contentType="text/html"%>

<style type="text/css">
      <!--
      .grid { border: #D1D1D1 1px solid; border-collapse: collapse; border-width: 1px; }
      .grid-header { background-color: #E7E7E7; color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; vertical-align: middle; }
      .grid-line { color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: normal; vertical-align: middle; }
      -->
</style>

<!-- Título da Página -->

<div id="form">

	<div id="formTitle">
		Módulo Operacional
	</div>
	<div id="formSubtitle">
		Administração de Propriedades do Sistema
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

<br>

<%
  String action = request.getParameter("action");
  String name = request.getParameter("propertyName");
  String value = request.getParameter("propertyValue");
  
  if (name == null) {
    name = "";
  }
  
  if (value == null) {
    value = "";
  }

  if ("search".equals(action)) {
    value = System.getProperty(name,"");
  }
  if ("add".equals(action) || "update".equals(action)) {
    System.setProperty(name,value);
  }
  if ("delete".equals(action)) {
    System.getProperties().remove(name);
  }
%>

<!-- Ações do Formulário -->
<script type="text/javascript">

  function doSubmit(action) {
    var form = document.forms[0];
    
    if (action == 'add' || action == 'update' || action=='delete') {
      if(!confirm('ATENÇÃO: Esta operação pode comprometer o funcionamento do ambiente.\nDeseja realmente realizar esta operação?')) {
        return;
      }
    }
    
    form.action.value = action;
    form.submit();
  }
  
  function doReset() {
    var form = document.forms[0];
    form.propertyName.value = '';
    form.propertyValue.value = '';
  }
</script>

<form action="environment.do" name="propertiesAdminForm">
<input type="hidden" name="action" value="">
<table border="0" cellpadding="0" cellspacing="0">
  <tr class="grid-line">
    <td colspan="2"><b>Propriedade</b></td>
  <tr>
  <tr class="grid-line">
    <td><b>Nome:</b></td>
    <td><input name="propertyName"  type="text" size="80" value="<%= name %>" class="textbox" /></td>
  <tr>
  <tr class="grid-line">
    <td><b>Valor:</b></td>
    <td><input name="propertyValue"  type="text" size="80" value="<%= value %>" class="textbox" /></td>
  <tr>
  <tr class="grid-line" align="center">
    <td colspan="2">
      <input name="btnSearchProperty" type="button" value="Buscar"    onclick="javascript:doSubmit('search')" class="button" />&nbsp;
      <input name="btnAddProperty"    type="button" value="Inserir"   onclick="javascript:doSubmit('add')"    class="button" />&nbsp;
      <input name="btnUpdateProperty" type="button" value="Atualizar" onclick="javascript:doSubmit('update')" class="button" />&nbsp;
      <input name="btnDeleteProperty" type="button" value="Remover"   onclick="javascript:doSubmit('delete')" class="button" />&nbsp;
      <input name="btnReset"          type="button" value="Reset"     onclick="javascript:doReset()"          class="button" />                              
    </td>
  </tr>
</table>
</form>
<br>
<div style="width: 450px; height: 200px; overflow: auto;">
<table class="grid">
  <tr class="grid-header">
    <th width="25%">Nome</th>
    <th width="75%">Valor</th>
  </tr>
<%
  java.util.Properties properties = System.getProperties();
  java.util.Enumeration keys = properties.propertyNames();

  String[] names = new String[properties.size()];
  int i = 0;
  while (keys.hasMoreElements() && i < names.length) {
    names[i] = (String) keys.nextElement();
    i++;
  }
  java.util.Arrays.sort(names);
  for (i = 0; i < names.length; i++) {
    String key = names[i];
    value = properties.getProperty(key, "");
%>
  <tr class="grid-line">
    <td align="left"><%= key %>:</td>
    <td align="left"><%= value %></td>
  </tr>
<%
  }
%>
</table>
</div>
</div>
