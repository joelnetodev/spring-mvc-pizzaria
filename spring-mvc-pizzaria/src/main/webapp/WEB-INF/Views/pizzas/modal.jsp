<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Modal -->
<div id="modal-pizza" class="modal fade" role="dialog">
  <div class="modal-dialog">
  
<form id="form-pizza" method="post">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Informações Pizza</h4>
      </div>
      <div class="modal-body">
        
        <input type="hidden" name="id" id="id" />
        
        <label for="nome">Nome:</label>
        <input id="nome" name="nome" class="form-control" />
        
         <label for="nome">Preço:</label>
        <input id="preco" name="preco" class="form-control" />
        
        <label for="categoria">Categoria:</label>
        <select id="categoria" name="categoria" class="form-control" select>
        <option>Selecione... </option>
        <c:forEach items="${categoriasenum}" var="categoriaenum">
			<option>${categoriaenum}</option>
		</c:forEach>
        </select>
        
        
        <br/>
        <div align="left" width="100%">
		<div style="display: inline-block"> <label for="ingredienteSelecionado">Ingrediente:</label>
        <select id="ingredienteSelecionado" name="ingredienteSelecionado" class="form-control">
        <option>Selecione... </option>
        <c:forEach items="${ingredientesLista}" var="ingredienteItem">
			<option value="${ingredienteItem.id}">${ingredienteItem.nome}</option>
		</c:forEach>
        </select></div>
		<div style="display: inline-block"> <button id="btn-add" type="button" class="btn btn-primary" onclick="funcaoAdicionarIngrediente()">Adicionar</button> </div>
		</div>
		
<br>
<table class="table table-hover table-condensed table-striped table-bodered" id="tabela-ingredientes" name="tabela-ingredientes">
<tr><td>Ingrediente</td><td>Deletar</td></tr>

</table>
       
      </div>
      
      <div class="modal-footer">
      	<button id="btn-salvar" type="button" class="btn btn-primary" onclick="funcaoSalvar()">Salvar</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
      </div>
    </div>
</form>

  </div>
</div>