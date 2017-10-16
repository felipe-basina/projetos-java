<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="org.apache.log4j.Level"%>
<%@ page import="org.apache.log4j.LogManager"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Enumeration"%>

<div id="form">

	<div id="formTitle">
		Módulo Operacional
	</div>
	<div id="formSubtitle">
		Administração Log4J
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

<br>

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
        <form action="log.do" name="logFilterForm">Filtro de Loggers:&nbsp;&nbsp;
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
          <th width="15%">Logger</th>
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
              String url = "log.do?operation=changeLogLevel&logger=" + loggerName + "&newLogLevel=" + logLevels[cnt] + "&logNameFilter=" + (logNameFilter != null ? logNameFilter : "");                                                
                                  
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
</div>
