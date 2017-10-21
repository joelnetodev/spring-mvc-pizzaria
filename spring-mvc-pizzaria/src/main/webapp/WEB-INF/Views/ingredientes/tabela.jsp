<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table class="table table-hover table-condensed table-striped table-bodered">

<tr><td>Nome</td><td>Categoria</td><td>Editar</td><td>Deletar</td></tr>

<c:forEach items="${ingredientes}" var="ingrediente">

<tr data-id="${ingrediente.id}"> 
<td>${ingrediente.nome}</td>
<td>${ingrediente.categoria}</td>
<td><button type="button" class="btn btn-primary" onclick="funcaoAlterar(this)">editar</button></td>
<td><button type="button" class="btn btn-danger" onclick="funcaoDeletar(this)">deletar</button></td>
</tr>

</c:forEach>

<tr><td colspan="4">Total: ${ingredientes.size()}</td></tr>
</table>
