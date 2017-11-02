<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzaria - Login</title>

<c:set var="endereco" value="${pageContext.request.contextPath}" scope="request"/>

<link rel="stylesheet" type="text/css" href="${endereco}/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${endereco}/resources/bootstrap/css/bootstrap-theme.min.css" />

		
</head>
<body>

<section class="container">

<jsp:include page="../menu/menu.jsp"></jsp:include>

<h1>Login</h1>
 
   <div class="container">

<!--cria o form com a ação da pagina de login e e o _csrf token para enviar os dados criptografados (pode disabilitar na websecurity com csrf.disable()) -->

						<div class="row">
							<div class="col-md-4 col-md-offset-3">
    						<form th:action="@{/login}" method="post">
								 <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="usuario" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Senha">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-primary" value="Log In">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
	</div>

</section>

<script type="text/javascript" src="${endereco}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${endereco}/resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>