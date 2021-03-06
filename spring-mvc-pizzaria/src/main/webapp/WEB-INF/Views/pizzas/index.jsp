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

<jsp:include page="../menu/menu.jsp"></jsp:include>

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

var funcaoAlterar = function(element)
{	
	var id = $(element).parents('tr').data('id');
	$.ajax({
		  url: "pizzas/buscar/" + id,
		  type: 'GET',
		  success: function(pizza)
		  {
			  //o retorno é um dto de pizza (json)
			  
			  var form = $("#form-pizza");
			  
			  form.find('#id').val(pizza.id);
			  form.find('#nome').val(pizza.nome);
			  form.find('#preco').val(pizza.preco);
			  form.find('#categoria').val(pizza.categoria);
			  
			  $("#ingredienteSelecionado").val($("#ingredienteSelecionado option:first").val());
			  var tabela = $("#tabela-ingredientes");
			  funcaoLimparTabela(tabela);
			  
			  for (i = 0; i < pizza.ingredientes.length; i++) 
			  { 
				  var ingrediente = pizza.ingredientes[i];
				  funcaoAdicionarLinhaTabela(tabela, ingrediente.id, ingrediente.nome);
			  }
			  			  
			  $("#modal-pizza").modal('show');
		  },
		  error: function()
		  {
			  alert('Erro');
		  }
	});
}

var cadastrarNovo = function()
{	
	var form = $("#form-pizza");
	form.find('#id').val(0);
	form.find('#nome').val('');
	form.find('#preco').val('');
	$("#categoria").val($("#categoria option:first").val());
	$("#ingredienteSelecionado").val($("#ingredienteSelecionado option:first").val());
	
	var tabela = $("#tabela-ingredientes");
	funcaoLimparTabela(tabela);
	
	$("#modal-pizza").modal('show');
}

var funcaoAdicionarIngrediente = function()
{
	var id = $('#ingredienteSelecionado option:selected').val()
	var nome = $("#ingredienteSelecionado option:selected").text();
	
	var tabela = $("#tabela-ingredientes");
	funcaoAdicionarLinhaTabela(tabela, id, nome);
}

var funcaoAdicionarLinhaTabela = function(tabela, id, nome)
{
	$(tabela).append('<tr data-id="' + id + '"><td>'+ nome +'</td><td><button type="button" class="btn btn-danger" onclick="funcaoDeletarIngrediente(this)">deletar</button></td></tr>');
}

var funcaoDeletarIngrediente = function(element)
{
	var id = $(element).parents('tr').remove();
}

var funcaoSalvar = function()
{
	var url = "pizzas/salvar/";	
	var pizza = obterPizzaJson();
		
	console.log(pizza);
	
	//requisição com ajaxa e json
	$.ajax({ 
	    url:url,
	    data: pizza,
	    contentType: "application/json",
	    type:"POST",
	    success: (function(paginaRetorno)
	    		{
	    			$("#secao-tabela").html(paginaRetorno.toString());
	    			$("#modal-pizza").modal('hide');
	    			alert('Salvo com sucesso.');
	    		})
		})
		.fail(function(data){
			  alert("Erro");
		});
}

function obterPizzaJson()
{
	var form = $("#form-pizza");
	
	//cria um objeto pizza
	var pizza = {
		id: Number(form.find('#id').val()),
		nome: form.find('#nome').val(), 
		preco: Number(form.find('#preco').val()),
		categoria: form.find('#categoria').val(),
		ingredientes: obterIngredientes()
		};

	//serializa com json
	return JSON.stringify(pizza);
} 

function obterIngredientes()
{
	var ingredientes = [];
	
	$("#tabela-ingredientes tr").each(function() 
	{
		var id = $(this).data('id');
		if(id != null && id != 0)
		{
			//Quando salvar ingrediente, colocar nome com '' e categoria nula 
			//(quando converte para dto o enum tem que ir vazio, e também da erro se nome for nulo)
			ingredientes.push({id: Number(id), nome: '', categoria: null});
		}
	});
	
	return ingredientes;
}


var funcaoLimparTabela = function(tabela)
{
	$(tabela).find("tr").each(function() 
	{
		//não deleta a primeira linha
		var id = $(this).data('id');
		if(id != null && id != 0)
		{
			$(this).remove();
		}
    });
}

</script>
	
</body>
</html>