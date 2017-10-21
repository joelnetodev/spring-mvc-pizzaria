<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzaria - Ingredientes</title>

<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

<link rel="stylesheet" type="text/css" href="${path}/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/bootstrap/css/bootstrap-theme.min.css" />

		
</head>
<body>
<section class="container">

<h1>${titulo}</h1>

<c:if test="${not empty mensagem}">
<div class="container">
<div class="alert alert-info">
${mensagem}
</div>
</div>
</c:if>

<!-- Tabela -->
<section id="secao-tabela">
<jsp:include page="tabela.jsp"></jsp:include>
</section>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-ingrediente">Cadastrar Ingrediente</button>

</section>

<!-- Modal -->
<jsp:include page="modal.jsp"></jsp:include>

<script type="text/javascript" src="${path}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${path}/resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">

var funcaoSalvar = function()
{
	var url = "ingredientes/salvar/";
	var ingrediente = $("#form-ingrediente").serialize();
		
	$.post(url, ingrediente)
	.done(function(paginaRetorno){
			
		$("#secao-tabela").html(paginaRetorno.toString());
		$("#modal-ingrediente").modal('hide');
		alert('Salvo com sucesso.');
		
		funcaoLimpar();
	})
	.fail(function(){
		alert('Erro')
	});
}

var funcaoUpdate = function(element)
{	
	var id = $(element).parents('tr').data('id');
	$.ajax({
		  url: "ingredientes/buscar/" + id,
		  type: 'GET',
		  success: function(ingrediente)
		  {
			  var form = $("#form-ingrediente");
			  
			  form.find('#nome').val(ingrediente.nome);
			  form.find('#categoria').val(ingrediente.categoria);
			  
			  $("#modal-ingrediente").modal('show');
		  },
		  error: function()
		  {
			  alert('Erro');
		  }
	});
}

var funcaoDelete = function(element)
{
	if(!confirm('Tem certeza que deseja deletar?'))
		return;
		
	var id = $(element).parents('tr').data('id');
	$.ajax({
		  url: "ingredientes/delete/" + id,
		  type: 'GET',
		  success: function(paginaRetorno)
		  {
			  $("#secao-tabela").html(paginaRetorno.toString());
			  alert('Deletado com sucesso.');
		  },
		  error: function()
		  {
			  alert('Erro');
		  }
	});
}

var funcaoLimpar = function()
{
	var form = $("#form-ingrediente");
	
	form.find('#id').val('');
	form.find('#nome').val('');
	form.find('#categoria').val('');
}

</script>
	
</body>
</html>