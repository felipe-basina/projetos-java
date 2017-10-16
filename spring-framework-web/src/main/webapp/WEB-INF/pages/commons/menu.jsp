<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.claro.com.br/claro-security-tags"%>

<script>
	//Coloque aqui o número de itens de menu
	n_divs = '9';
</script>

<ul id="menu">
	<li>
		<a href="#" onclick="javascript:void(escondediv('1',n_divs));"><spring:message code="label.feature.a" /></a>	
	</li>	
	<div id="mdiv1" style="display: none">
		<li>
			<a id="um" href="datetime.do" class="link_smenu" ><spring:message code="label.datetime" /></a>
		</li>
		<li>
			<a id="dois" href="#" class="link_smenu" >Teste 02</a>
		</li>
	</div>

	<li>
		<a href="#" onclick="javascript:void(escondediv('2',n_divs));"><spring:message code="label.feature.b" /></a>
	</li>	
	<div id="mdiv2" style="display: none">
		<li>
			<a id="tres" href="#" class="link_smenu" >Teste 03</a>
		</li>
		<li>
			<a id="quatro" href="#" class="link_smenu" >Teste 04</a>
		</li>
	</div>
	
	
	<li><a href="helloworld.do"><spring:message
				code="label.example" /></a></li>
	<li><a href="contatoservlet.cs"><spring:message
				code="label.contato.servlet" /></a></li>
	<li><a href="exibese.do"><spring:message
				code="label.se.senao" /></a></li>
	<li><a href="trueorfalse.do"><spring:message
				code="label.se.senao" /> v02</a></li>
	<li><a href="init.do"><spring:message
				code="label.crud.menu" /></a></li>
	<li><a href="tabs.do"><spring:message
				code="label.tabs" /></a></li>
	<li><a href="test.do"><spring:message code="label.test" /></a></li>
	<li><a href="test2.do"><spring:message code="label.test" /> 2</a></li>
	<!-- li><a href="contacts.do"><spring:message code="label.exit" /></a></li-->
	<security:isInRole roles="appGrpOper,appGrpAdmin">
		<li><a href="environment.do">Environment</a></li>
		<li><a href="log.do">Log4J</a></li>
		<li><a href="#" onclick="javascript:void(escondediv('3',n_divs));">Contacts Manager</a></li>
		<div id="mdiv3" style="display: none">
			<li>
				<a id="cinco" href="contacts.do" class="link_smenu" >Adicionar</a>
			</li>
			<li>
				<a id="seis" href="getContacts.do" class="link_smenu" >Listar</a>
			</li>
		</div>
		<li><a href="<spring:message code="label.webservice.link" />" target="_blank"><spring:message
				code="label.webservice" /></a></li>
	</security:isInRole>
	<li><a href="logout.do"><spring:message code="label.exit" /></a></li>
</ul>