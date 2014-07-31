<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Novo Aluno</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="logout" value="/j_spring_security_logout" ></c:url>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8"/>
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/index.css" rel="stylesheet" type="text/css">

    <script src="${src}/js/jquery.js"></script>
    <script src="${src}/js/bootstrap.min.js"></script>
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
  	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  			<div class="navbar-header">
    			<button type="button" class="navbar-toggle" data-toggle="collapse"
    			data-target="#bs-example-navbar-collapse-1">
      				<span class="sr-only">Toggle navigation</span>
      				<span class="glyphicon glyphicon-align-justify"></span>
    			</button>
    			<a class="navbar-brand" href="${home}perfil">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				<li class="active"><a href="${home}perfil">Perfil</a></li>
      				<li><a href="${home}perfil/amigos">Amigos</a></li>
      				<li><a href="${home}perfil/turmas">Minhas Turmas</a></li>
      				<li><a href="${home}perfil/arquivos">Arquivos</a></li>
      				
      				<li class="dropdown">
        				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
        					<b class="glyphicon glyphicon-search"></b></a>
        				<ul class="dropdown-menu">
          					<li><a href="${home}busca/Pessoas">Pessoas</a></li>
          					<li><a href="${home}busca/Turmas">Turmas</a></li>
        			</ul></li>
    			</ul>
    			<ul class="nav navbar-nav navbar-right">
      				<li class="dropdown">
        				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
        					<b class="glyphicon glyphicon-cog"></b></a>
        				<ul class="dropdown-menu">
          					<li><a href="${home}perfil/preferencias">Preferencias</a></li>
          					<li class="divider"></li>
          					<c:url var="profile" value="/perfil"/>
          					<li><a href="${logout}">Sair</a></li>
        		</ul></li></ul>

  				<p class="navbar-text navbar-right"><sec:authentication property="details.nome"/></p>
  			</div>
		</nav>
		
		<div class="jumbotron">
  			<h1>${sessionScope.turma.nome}</h1>
  			<p>${sessionScope.descricao}</p>
  		</div>

	<div class="main">
		<aside>
			<ul class="nav nav-pills">
				<li><a href="#"><span class="badge pull-right">${sessionScope.turma.dono}</span>
					Professor
				</a></li>
  				<li><a href="${home}turma/alunos"><span class="badge pull-right">?</span>
      			Membros
    			</a></li>
    			<li><a href="#"><span class="badge pull-right">?</span>
    				Posts
    			</a></li>
    			<li><a href="${home}turma/notificacoes"><span class="badge pull-right">?</span>
    				Notificações
    			</a></li>
			</ul>
		</aside>
		
		<table class="table table-hover">
			<thead>
				<tr><td>Nome</td></tr>
			</thead>
			<tbody>
				<c:forEach var="aluno" items="${alunos}">
					<tr>
						<td>${aluno.nome}</td>
						<td><a href="${home}turma/convidarAluno?idAluno=${aluno.id}" class="btn btn-default">Convidar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
  </div>

		
  	
  </body>
</html>