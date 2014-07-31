<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Arquivos</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="logout" value="/j_spring_security_logout" ></c:url>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8"/>
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/index.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/arqivos.css">

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
      				<li class="active"><a href="${home}perfil/arquivos">Arquivos</a></li>
      				
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
		<!-- 
		<div class="info">
			<p>N Arquivos</p>
			<div class="progress panel pnel-default">
  				<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
    				{espaço}%
  				</div>
			</div>
		</div>
		 -->
		<form role="form" action="${home}arquivos/upload" enctype="multipart/form-data" method="post">
			<div class="form-group">
				<input type="file" name="file"><br/>
				<input class="btn btn-success" type="submit" value="Salvar Arquivo">
			</div>
		</form>
		<table class="table table-hover">
			<thead>
				<tr><td>Nome</td><td>Formato</td><td>Opções</td></tr>
			</thead>
			<tbody>
				<c:forEach var="arquivo" items="${arquivos}">
					<tr>
						<td>${arquivo.nome}</td>
						<td>${arquivo.formato}</td>
						<td>
							<a class="btn btn-success" href="${home}arquivos/download?idArquivo=${arquivo.id}">Baixar</a>
							<a class="btn btn-danger" href="${home}arquivos/excluir?idArquivo=${arquivo.id}">Excluir</a>
						</td>
				</c:forEach>
			</tbody>
		</table>

		
  	
  </body>
</html>