<%@ page language="java" contentType="text/html"%>

<html>
  <head>
    <title>[Módulo Operacional] - Administração de Propriedades do Sistema</title>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Expires" content="0">
    <style type="text/css">
      <!--
      .page-title { color: #FF1A00; font-size: 18px; font-weight: bold; padding-left: 5px; text-align: left;}
      .page-subtitle { border-top: #D1D1D1 3px solid; background-color: #E7E7E7; color: #FF1A00; font-size: 10px; font-weight: bold; height: 18px; padding-left: 5px; text-align: left;}
      body { background-color: #FFFFFF; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; margin: 0px; }
      .grid { border: #D1D1D1 1px solid; border-collapse: collapse; border-width: 1px; }
      .grid-header { background-color: #E7E7E7; color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; vertical-align: middle; }
      .grid-line { color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: normal; vertical-align: middle; }
      a, a:link, a:visited, a:active { color: red; text-decoration: none; text-transform: uppercase; }
      .button { background-color: #E3E3E3; border: 1px solid #8C8C8C; color: #666666; cursor: hand; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; }
      .textbox { background-color: #FFFFFF; border: #D6D6D6 1px solid; color: #666666;  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; vertical-align: middle; scrollbar-3dlight-color: #8C8C8C; scrollbar-arrow-color: #666666; scrollbar-base-color: #E3E3E3; scrollbar-darkshadow-color: #8C8C8C; scrollbar-face-color: #E3E3E3; scrollbar-highlight-color: #E3E3E3;	scrollbar-shadow-color: #E3E3E3; scrollbar-track-color: #E3E3E3; }
      -->
    </style>
  </head>

<body>

<!-- Título da Página -->
</br>
<table border="0" cellpadding="0" cellspacing="0" width="450">
  <tr>
    <td class="page-title">Módulo Operacional</td>
  </tr>
  <tr>
    <td class="page-subtitle">Administração de Propriedades do Sistema</td>
  </tr>
</table>
</br>

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

<form action="propertiesAdmin.jsp" name="propertiesAdminForm">
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

<table border="1" class="grid" width="100%">
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
    <td><%= key %>:</td>
    <td><%= value %></td>
  </tr>
<%
  }
%>
</table>

</body>
</html>