<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
	<title>Cadastro</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/index.css" rel="stylesheet" type="text/css">

    <script src="${src}/js/jquery.js"></script>
    <script src="${src}/js/bootstrap.min.js"></script>
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  		<div class="navbar-header">
    		<a class="navbar-brand" href="${home}">SysRedIN</a>
  		</div>
  	</nav>
  	
  	<div class="main">
  		<form role="form" action="${home}cadastro/salvar" method="post" class="cadastro" id="cadastro">
  			<div class="form-group">
  				<label for="nome">Nome</label>
  				<input value="${perfil.nome}" type="text" name="nome" class="form-control" id="nome" placeholder="Digite seu nome completo">
  			</div>
  			<div class="form-group">
    			<label for="exampleInputEmail1">Email</label>
    			<input value="${perfil.email}" type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Digite seu E-mail">
  			</div>
  			<div class="form-group">
    			<label for="Senha">Senha</label>
    			<input value="${perfil.senha}" type="password" name="senha" class="form-control" id="Senha" placeholder="Digite sua senha">
  			</div>
  			<div class="form-group">
  				<label for="confSenha">Confirmar Senha</label>
  				<input type="password" name="confsenha" class="form-control" id="confSenha" placeholder="Cofirmar Senha" />
  			</div>
  			<div class="form-group">
  				<label for="tipoPerfil">Você é um...</label>
  				<select id="tipoPerfil" name="tipoPerfil" class="form-control">
  					<c:forEach var="tipo" items="${tipos}">
  						<option value="${tipo.id}">${tipo.nome}</option>
  					</c:forEach>
  				</select>
  			</div>
  			<div class="form-group">
  				<label for="instituicao">Instituição</label>
  				<select id="instituicao" name="idInstituicao" class="form-control">
  					<c:forEach var="instituicao" items="${instituicoes}">
  						<option value="${instituicao.id}">${instituicao.nome}</option>
  					</c:forEach>
  				</select>
  			</div>
  			<button type="submit" class="btn btn-success" value="salvar">Submit</button>
  		</form>

  	
  	
  	</div>
</body>