<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table class="table table-hover table-condensed table-striped table-bodered">

<tr><td>Nome</td><td>Categoria</td><td>Preco</td><td>Ingredientes</td><td>Editar</td><td>Deletar</td></tr>

<c:forEach items="${pizzas}" var="pizza">

<tr data-id="${pizza.id}"> 
<td>${pizza.nome}</td>
<td>${pizza.preco}</td>
<td>${pizza.categoria}</td>

<td>
<c:forEach items="${pizza.ingredientes}" var="ingrediente">
${ingrediente.nome},
</c:forEach>
</td>

<td><button type="button" class="btn btn-primary" onclick="funcaoAlterar(this)">editar</button></td>
<td><button type="button" class="btn btn-danger" onclick="funcaoDeletar(this)">deletar</button></td>
</tr>

</c:forEach>

<tr><td colspan="4">Total: ${ingredientes.size()}</td></tr>
</table>
