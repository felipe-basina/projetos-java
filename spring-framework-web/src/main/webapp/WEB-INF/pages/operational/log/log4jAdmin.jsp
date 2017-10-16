<%@ page language="java" contentType="text/html"%>

<%@ page import="org.apache.log4j.Level"%>
<%@ page import="org.apache.log4j.LogManager"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.*"%>

<!-- 
  THE REASON THIS UTILITY WAS WRITTEN USING SCRIPLET AND NOT TAGS OR UTILS 
  WAS EASE OF INSTALLATION AND CONFIGURATION WAS THE HIGHEST PRIORITY ALONG 
  WITH THE LEAST NUMBER OF DEPENDANCIES.  THE ONLY THING THIS ADMIN UTILITY 
  REQUIRES IS A JSP ENGINE AND LOG4J.  NO CONFIGURATION IS REQUIRED EXCEPT 
  DROPPING IN THE JSP PAGE AND INVOKING IT THROUGH A STANDARD WEB BROWSER.
-->

<html>
  <head>
    <title>[Módulo Operacional] - Administração Log4J</title>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Expires" content="0">
    <style type="text/css">
      <!--
      .page-title { color: #FF1A00; font-size: 18px; font-weight: bold; padding-left: 5px; text-align: left;}
      .page-subtitle { border-top: #D1D1D1 3px solid; background-color: #E7E7E7; color: #FF1A00; font-size: 10px; font-weight: bold; height: 18px; padding-left: 5px; text-align: left;}
      body { background-color: #FFFFFF; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; margin: 0px; }
      .textbox { background-color: #FFFFFF; border: #D6D6D6 1px solid; color: #666666;  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; vertical-align: middle; scrollbar-3dlight-color: #8C8C8C; scrollbar-arrow-color: #666666; scrollbar-base-color: #E3E3E3; scrollbar-darkshadow-color: #8C8C8C; scrollbar-face-color: #E3E3E3; scrollbar-highlight-color: #E3E3E3;	scrollbar-shadow-color: #E3E3E3; scrollbar-track-color: #E3E3E3; }
      .button { background-color: #E3E3E3; border: 1px solid #8C8C8C; color: #666666; cursor: hand; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; }
      .grid { border: #D1D1D1 1px solid; border-collapse: collapse; border-width: 1px; }
      .grid-header { background-color: #E7E7E7; color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; vertical-align: middle; }
      .grid-line { color: #666666; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: normal; vertical-align: middle; }
      a, a:link, a:visited, a:active { color: red; text-decoration: none; text-transform: uppercase; }
      -->
    </style>
  </head>
  <body onLoad="javascript:document.logFilterForm.logNameFilter.focus();">
  
<!-- Título da Página -->
</br>
<table border="0" cellpadding="0" cellspacing="0" width="450">
  <tr>
    <td class="page-title">Módulo Operacional</td>
  </tr>
  <tr>
    <td class="page-subtitle">Administração Log4J</td>
  </tr>
</table>
</br>

    <% 
      String containsFilter = "Contains";
      String beginsWithFilter = "Begins With";
      
      String[] logLevels = { "trace", "debug", "info", "warn", "error", "fatal", "off" };      

      String targetOperation   = (String)request.getParameter("operation");
      String targetLogger      = (String)request.getParameter("logger");
      String targetLogLevel    = (String)request.getParameter("newLogLevel");
      String logNameFilter     = (String)request.getParameter("logNameFilter");
      String logNameFilterType = (String)request.getParameter("logNameFilterType");
    %>
    <div id="content">
      <div>
        <form action="log4jAdmin.jsp" name="logFilterForm">Filtro de Loggers:&nbsp;&nbsp;
          <input name="logNameFilter" type="text" size="50" value="<%=(logNameFilter == null ? "":logNameFilter)%>" class="textbox" />
          <input name="logNameFilterType" type="submit" value="<%=beginsWithFilter%>" class="button" />&nbsp;
          <input name="logNameFilterType" type="submit" value="<%=containsFilter%>" class="button" />&nbsp;          
          <input name="logNameClear" type="button" value="Clear" class="button" onmousedown='javascript:document.logFilterForm.logNameFilter.value="";' />                    
          <input name="logNameReset" type="reset" value="Reset" class="button" />                              
          <param name="operation" value="changeLogLevel" />
        </form>
      </div>
      <table cellspacing="1" border="1" class="grid">
        <tr class="grid-header">
          <th width="25%">Logger</th>
          <th width="25%">Parent Logger</th>
          <th width="15%">Level</th>
          <th width="35%">Change Log Level To</th>
        </tr>
        <%                    
          Enumeration loggers = LogManager.getCurrentLoggers();
          HashMap loggersMap = new HashMap(128);
          
          Logger rootLogger = LogManager.getRootLogger();
          
          if(!loggersMap.containsKey(rootLogger.getName()))
          {
            loggersMap.put(rootLogger.getName(), rootLogger);
          }
                         
          while(loggers.hasMoreElements())
          {
            Logger logger = (Logger)loggers.nextElement();    
            
            if(logNameFilter == null || logNameFilter.trim().length() == 0)
            {
                loggersMap.put(logger.getName(), logger);                                                
            }
            else if(containsFilter.equals(logNameFilterType))
            {
              if(logger.getName().toUpperCase().indexOf(logNameFilter.toUpperCase()) >= 0)
              {
                loggersMap.put(logger.getName(), logger);                                                                
              }
            }
            else
            {
              // Either was no filter in IF, contains filter in ELSE IF, or begins with in ELSE
              if(logger.getName().startsWith(logNameFilter))
              {
                loggersMap.put(logger.getName(), logger);                                                                
              }              
            }  
          }
                          
          Set loggerKeys = loggersMap.keySet();
          String[] keys = new String[loggerKeys.size()];
          keys = (String[])loggerKeys.toArray(keys);
          Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
          
          for(int i=0; i<keys.length; i++)
          {
            Logger logger = (Logger)loggersMap.get(keys[i]);

            // MUST CHANGE THE LOG LEVEL ON LOGGER BEFORE GENERATING THE LINKS AND THE
            // CURRENT LOG LEVEL OR DISABLED LINK WON'T MATCH THE NEWLY CHANGED VALUES
            if("changeLogLevel".equals(targetOperation) && targetLogger.equals(logger.getName()))
            {
              Logger selectedLogger = (Logger)loggersMap.get(targetLogger);
              selectedLogger.setLevel(Level.toLevel(targetLogLevel));
            }  

            String loggerName = null;
            String loggerEffectiveLevel = null;
            String loggerParent = null;
            
            if(logger != null)
            {
              loggerName = logger.getName();
              loggerEffectiveLevel = String.valueOf(logger.getEffectiveLevel());
              loggerParent = (logger.getParent() == null ? null : logger.getParent().getName());
            }
        %>

        <tr class="grid-line">
          <td><%=loggerName%></td>
          <td><%=loggerParent%></td>
          <td><%=loggerEffectiveLevel%></td>
          <td class="center">
          <%                            
            for(int cnt=0; cnt<logLevels.length; cnt++)
            {
              String url = "log4jAdmin.jsp?operation=changeLogLevel&logger=" + loggerName + "&newLogLevel=" + logLevels[cnt] + "&logNameFilter=" + (logNameFilter != null ? logNameFilter : "");                                                
                                  
              if(logger.getLevel() == Level.toLevel(logLevels[cnt]) || logger.getEffectiveLevel() == Level.toLevel(logLevels[cnt]))
              {
          %>              
                [<%=logLevels[cnt].toUpperCase()%>]                            
          <%                
              }
              else
              {                        
          %>              
                <a href='<%=url%>'>[<%=logLevels[cnt]%>]</a>&nbsp;
          <%
              }
            }                                                                                
          %>
          </td>
        </tr>
       <% 
         } 
       %>                        
      </table>
    </div>
  </body>
</html>