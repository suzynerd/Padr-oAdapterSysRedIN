<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>SysRedIN</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="sec" value="/j_spring_security_check" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/home.css" rel="stylesheet" type="text/css">
    <script src="${src}/js/jquery.js"></script>
    <script src="${src}/js/bootstrap.min.js"></script>
    <script src="${src}/js/home.js"></script>
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
    		<a class="navbar-brand" href="#">SysRedIN</a>
  		</div>
  		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    		<ul class="nav navbar-nav navbar-right">
    			<li><a href="${home}cadastro">Cadastrar</a></li>
      			<li class="dropdown">
        			<a href="#" class="dropdown-toggle" data-toggle="dropdown">Entrar <b class="caret"></b></a>
        			<ul class="dropdown-menu">
        				<li><form role="form" method="post" action="${sec}" class="login">
        					<div class="form-group">
        						<input  name="j_username" type="email" class="form-control" placeholder="E-mail">
        					</div>
        					<div class="form-group">
        						<input  name="j_password" type="password" class="form-control" placeholder="Senha">
        					</div>
       						<button type="submit" class="btn btn-success" value="login">Confirmar</button>
       						</form>
        </li></ul></li></ul></div></nav>

</body>
</html>