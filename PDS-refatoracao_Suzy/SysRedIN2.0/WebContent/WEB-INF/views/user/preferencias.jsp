<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Configurações</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="logout" value="/j_spring_security_logout" ></c:url>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">

	<link rel="stylesheet" type="text/css" href="${src}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${src}/css-aux/index.css">
	<link rel="stylesheet" type="text/css" href="${src}/css-aux/preferencias.css">

	<script language="JavaScript" type="text/javascript" src="${src}/js/jquery.js"></script>
	<script src="${src}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${src}/js/preferencias.js"></script>
	
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
							<li class="active" ><a href="${home}perfil/preferencias">Preferencias</a></li>
							<li class="divider"></li>
							<c:url var="profile" value="/perfil"/>
							<li><a href="${logout}">Sair</a></li>
				</ul></li></ul>

				<p class="navbar-text navbar-right"><sec:authentication property="details.nome"/></p>
			</div>
		</nav>

		<aside>
			<ul class="nav nav-pills">
  				<li id="li1"><a href="#" id="edit">Editar Perfil</a></li>
  				<li id="li2"><a href="#" id="senha">Mudar Senha</a></li>
  				<li id="li3"><a href="#" id="delete">Excluir Perfil</a></li>
			</ul>
		</aside>

		<div id="main" class="well">
			<form role="form" id="form-edit" action="${home}perfil/alterarDados" class="forms" method="GET">
				<div class="form-group">
					<label for="nome">Nome</label>
					<input class="form-control" type="text" id="nome" name="nome" value="${perfil.nome}" />
				</div>
				<div class="form-group">
					<label for="email">E-mail</label>
					<input class="form-control" type="email" id="email" name="email" value="${perfil.email}"/>
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
			</form>
			
			<form role="form" id="form-senha" action="${home}perfil/mudarSenha" class="forms" method="get">
				<div class="form-group">
					<label for="senhaAtual">Senha Atual</label>
					<input id="senhaAtual" name="senhaAtual" type="password" class="form-control" value="${senhaAtual}"/>
				</div>
				<div class="form-group">
					<label for="novaSenha">Nova Senha</label>
					<input id="novaSenha" name="novaSenha" type="password" class="form-control" value="${novaSenha}"/>
				</div>
				<div class="form-group">
					<label for="confSenha">Confirme sua nova Senha</label>
					<input id="confSenha" type="password" class="form-control" value="${confSenha}"/>
				</div>
				<button class="btn btn-success" id="bSenha" type="submit">Salvar</button>
			</form>

			<form role="form" id="form-delete" class="forms" mehod="post" action="${home}perfil/excluirPerfil">
				<label for="confCodigo">Digite o Código: </label>
				<p id="codigo"></p>
				<input id="confCodigo" type="text" class="form-control">
				<button class="btn btn-danger" disabled="disabled" id="bDelete">Excluir Conta</button>
			</form>
		</div>
  </body>
</html>