<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br>
<nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a href="${endereco}/inicio" class="navbar-brand">PIZZARIA</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="${endereco}/ingredientes">Ingredientes</a></li>
              <li><a href="${endereco}/pizzas">Pizzas</a></li>
              
            </ul>
            
            <c:if test="${not empty pageContext.request.userPrincipal.name}" >
            
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">${pageContext.request.userPrincipal.name}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="${endereco}/logout">Sair</a></li>
                </ul>
              </li>
            </ul>  
            
            </c:if>
            
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>