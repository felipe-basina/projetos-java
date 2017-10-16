<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/commons/tags.jsp" %>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="iso-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Simple Sidebar - Start Bootstrap Template</title>
	
	<spring:url value="/resources/css/bootstrap.min.css" var="btsminCss" />
	<link href="${btsminCss}" rel="stylesheet" />
		
	<spring:url value="/resources/css/simple-sidebar.css" var="simplesbCss" />
	<link href="${simplesbCss}" rel="stylesheet" />

	<spring:url value="/resources/css/jquery-ui-1.8.4.custom.css" var="juicustomCss" />
	<link href="${juicustomCss}" rel="stylesheet" />
	
	<spring:url value="/resources/css/table.css" var="tableCss" />
	<link href="${tableCss}" rel="stylesheet" />
		
	<spring:url value="/resources/css/jquery-ui.css" var="juiCss" />
	<link href="${juiCss}" rel="stylesheet" />
	
	<spring:url value="/resources/css/portal.css" var="portalCss" />
	<link href="${portalCss}" rel="stylesheet" />	

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#" class="menu-item" id="main-menu-item">Start Bootstrap</a>
                </li>
                <li>
                    <a href="#" class="menu-item" id="dashboard-menu-item">Dashboard</a>
                </li>
                <li>
                    <a href="#" class="menu-item" id="shortcuts-menu-item">Shortcuts</a>
                </li>
                <li>
                    <a href="#" class="menu-item" id="todosave-menu-item">Register todo</a>
                </li>
                <li>
                    <a href="#" class="menu-item" id="usersave-menu-item">Register user</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
		
            <div id="main" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Simple Sidebar</h1>
                        <p>This template has a responsive menu toggling system. The menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will appear/disappear. On small screens, the page content will be pushed off canvas.</p>
                        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>.</p>
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
						<button class="action btn btn-default">Clica aqui!</button>
                    </div>
                </div>
            </div>
			
            <div id="dashboard" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Dashboard menu item</h1>
                        <p>That's it!</p>
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle2">Toggle Menu 2</a>
						<button class="action btn btn-default">Clica aqui!</button>
                    </div>
                </div>
            </div>
			
            <div id="shortcuts" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Shortcuts menu item</h1>
                        <p>That's it!</p>
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle3">Toggle Menu 3</a>
						<button class="action btn btn-default">Clica aqui!</button>
                    </div>
                </div>
            </div>

            <div id="usersave" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Register</h1>
                        <p></p><a href="#menu-toggle" class="btn btn-default" id="menu-toggle3">Toggle Menu</a>
						<p></p>
						<div class="form-horizontal msg-alerta-register" style="display: none;"></div>
						<div class="form-horizontal msg-sucesso" style="display: none;"></div>
						<form id="dataUserform" method="post" class="form-horizontal">
							<input type="hidden" id="idval" value="" name="id" />
							<div class="form-group">
								<label class="col-sm-2 control-label">Name: </label>
								<input class="input required error form-control" type="text" name="name" id="name" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Last name: </label>
								<input class="input required error form-control" type="text" name="lastName" id="lastName" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">E-mail: </label>
								<input class="input required error form-control" type="text" name="email" id="email" />
							</div>
							<div class="align-center">					
						  		<input type="submit" class="btn btn-success" id="submit-user-form" value="submit" /> 
						  		<input type="button" class="btn clean" id="cleanform" value="clean" />
						  	</div><!-- Botões bootstrap: http://v4-alpha.getbootstrap.com/components/buttons/ -->
					</form>
                    </div>
                    <div id="show-users" style="display: none;">
                    	<div class="msg-alerta" style="display: none;"></div>
                    
						<div class="container" style="padding-top: 15px;">
							<table class="display" id="example" border="0">
								<thead>
									<tr align="center">
										<th># Id</th>
										<th>Name</th>
										<th>Last Name</th>
										<th>Email</th>
										<th>Creation date</th>
										<th>Update date</th>
									</tr>
								</thead><!-- odd gradeX even gradeC -->
								<tbody class="tbody">								
								</tbody>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>				
							</table>
						</div>
						<div class="align-center">
							<input type="button" class="btn btn-info" id="select-user-update" value="Update" />
							<input type="button" class="btn btn-danger" id="select-user-delete" value="Delete" />
						</div>
					</div>
                </div>
            </div>

            <div id="todosave" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Register</h1>
                        <p></p><a href="#menu-toggle" class="btn btn-default" id="menu-toggle3">Toggle Menu</a>
						<p></p>
						<div class="form-horizontal msg-alerta-register" style="display: none;"></div>
						<div class="form-horizontal msg-sucesso" style="display: none;"></div>
						<form id="dataTodoform" method="post" class="form-horizontal">
							<input type="hidden" id="idvaltodo" value="" name="id" />
							<div class="form-group">
								<label class="col-sm-2 control-label">User (e-mail): </label>
								<select class="input required error form-control" name="emailSelect" id="emailSelect"></select>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Date: </label>
								<input class="input required error form-control" type="text" name="creationDate" id="creationDate" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Title: </label>
								<input class="input required error form-control" type="text" name="title" id="title" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Description: </label>
								<textarea class="input required error form-control" rows="3" name="description" id="description"></textarea>
							</div>
							<div class="align-center">					
						  		<input type="submit" class="btn btn-success" id="submit-todo-form" value="submit" /> 
						  		<input type="button" class="btn clean" id="cleanform" value="clean" />
						  	</div><!-- Botões bootstrap: http://v4-alpha.getbootstrap.com/components/buttons/ -->
					</form>
                    </div>
                    <div id="show-todos" style="display: none;">
                    	<div class="msg-alerta" style="display: none;"></div>
                    
						<div class="container" style="padding-top: 15px;">
							<table class="display" id="tabletodo" border="0">
								<thead>
									<tr align="center">
										<th># Id</th>
										<th>Title</th>
										<th>Description</th>
										<th>Owner</th>
										<th>Scheduled date</th>
									</tr>
								</thead><!-- odd gradeX even gradeC -->
								<tbody class="tbody">								
								</tbody>
								<tfoot>
									<tr>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</tfoot>				
							</table>
						</div>
						<div class="align-center">
							<input type="button" class="btn btn-info" id="select-todo-update" value="Update" />
							<input type="button" class="btn btn-danger" id="select-todo-delete" value="Delete" />
						</div>
					</div>
                </div>
            </div>
			
            <!-- <div id="todoform" class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Fill in the inputs text and submit the form</h1>
                        <p></p><a href="#menu-toggle" class="btn btn-default" id="menu-toggle3">Toggle Menu</a>
						<p></p>
						<form id="dataform" method="post" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">Date: </label>
								<input class="input required error form-control" type="text" name="dateform" id="dateform" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Title: </label>
								<input class="input required error form-control" type="text" name="titleform" id="titleform" />
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Description: </label>
								<textarea class="input required error form-control" rows="3" name="descriptionform" id="descriptionform"></textarea>
							</div>							
						  <input type="submit" class="btn btn-default" id="submitform" value="submit" /> 
						  <input type="button" class="btn clean" id="cleanform" value="clean" />
					</form>
                    </div>
                </div>
            </div>-->
			
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
	
	<spring:url value="/resources/js/jquery.js" var="jqryJs" />
	<script type="text/javascript" src="${jqryJs}"></script>

	<spring:url value="/resources/js/jquery-ui.js" var="jqryuiJs" />
	<script type="text/javascript" src="${jqryuiJs}"></script>

	<spring:url value="/resources/js/jquery.dataTables.min.js" var="jdbmJs" />
	<script type="text/javascript" src="${jdbmJs}"></script>

	<spring:url value="/resources/js/jquery.form.js" var="jqryfJs" />
	<script type="text/javascript" src="${jqryfJs}"></script>

	<spring:url value="/resources/js/jquery.validate.js" var="jqryvJs" />
	<script type="text/javascript" src="${jqryvJs}"></script>

	<spring:url value="/resources/js/bootstrap.min.js" var="btsmJs" />
	<script type="text/javascript" src="${btsmJs}"></script>

	<spring:url value="/resources/js/portal.js" var="portalJs" />
	<script type="text/javascript" src="${portalJs}"></script>

	<spring:url value="/resources/js/todo.js" var="todoJs" />
	<script type="text/javascript" src="${todoJs}"></script>

	<spring:url value="/resources/js/user.js" var="userJs" />
	<script type="text/javascript" src="${userJs}"></script>

</body>

</html>