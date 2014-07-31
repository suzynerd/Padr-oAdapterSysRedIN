<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${perfil.nome}</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="logout" value="/j_spring_security_logout" ></c:url>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/index.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/viewPerfil.css" rel="stylesheet" type="text/css">
	
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
    			<a class="navbar-brand" href="${home}">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				<li><a href="${home}perfil">Perfil</a></li>
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
			<img src="http://placehold.it/125x170">
			<div class="info">
	  			<h1>${perfil.nome}</h1>
	  			<h4>${perfil.email}</h4>
	  			<p>${pefil.instituicao}</p>
	  		</div>
	  		<div class="extra">
	  			<ul class="nav nav-pills">
  					
      				<li><a href="#"><span class="badge pull-right">${infoAmigos}</span>
      						Amigos</a></li>
      				
				</ul>
	  		</div>
	  		<c:if test="${amigo == false}">
      			<a class="btn btn-primary btn-lg" role="button" href="${home}adicionarAmigo?idAmigo=${perfil.id}">Adicionar Amigo</a>
      		</c:if>
  		</div>
  </body>
</html>