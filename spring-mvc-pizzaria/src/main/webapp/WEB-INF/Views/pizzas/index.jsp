<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzaria - Pizzas</title>

<c:set var="endereco" value="${pageContext.request.contextPath}" scope="request"/>

<link rel="stylesheet" type="text/css" href="${endereco}/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${endereco}/resources/bootstrap/css/bootstrap-theme.min.css" />

		
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

<button type="button" class="btn btn-primary" onclick="cadastrarNovo()">Cadastrar Pizza</button>

</section>

<!-- Modal -->
<jsp:include page="modal.jsp"></jsp:include>

<script type="text/javascript" src="${endereco}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${endereco}/resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">

var cadastrarNovo = function(element)
{	
	var form = $("#form-pizza");
	form.find('#id').val(0);
	form.find('#nome').val('');
	form.find('#preco').val('');
	$("#categoria").val($("#categoria option:first").val());
	$("#ingredienteSelecionado").val($("#ingredienteSelecionado option:first").val());
	
	$("#modal-pizza").modal('show');
}

var funcaoSalvar = function()
{
	var url = "pizzas/salvar/";
	var ingrediente = $("#form-pizza").serialize();
		
	$.post(url, ingrediente)
	.done(function(paginaRetorno){
			
		$("#secao-tabela").html(paginaRetorno.toString());
		$("#modal-pizza").modal('hide');
		
		alert('Salvo com sucesso.');
		
	})
	.fail(function(){
		alert('Erro')
	});
}

var funcaoAlterar = function(element)
{	
	var id = $(element).parents('tr').data('id');
	$.ajax({
		  url: "pizzas/buscar/" + id,
		  type: 'GET',
		  success: function(pizza)
		  {
			  var form = $("#form-pizza");
			  
			  form.find('#id').val(pizza.id);
			  form.find('#nome').val(pizza.nome);
			  form.find('#preco').val(pizza.preco);
			  form.find('#categoria').val(pizza.categoria);
			  
			  $("#ingredienteSelecionado").val($("#ingredienteSelecionado option:first").val());
			  
			  for (i = 0; i < pizza.ingredientes.length; i++) 
			  { 
				  
			  }
			  			  
			  $("#modal-pizza").modal('show');
		  },
		  error: function()
		  {
			  alert('Erro');
		  }
	});
}

var funcaoDeletar = function(element)
{
	if(!confirm('Tem certeza que deseja deletar?'))
		return;
		
	var id = $(element).parents('tr').data('id');
	$.ajax({
		  url: "pizzas/deletar/" + id,
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

</script>
	
</body>
</html>