<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<sec:authentication var="principal" property="principal" />



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
          
          <sec:authorize access="isAuthenticated()">
                     
          <ul class="nav navbar-nav">
                     
           <!-- Verifico as permissoes do usuario para montar o menu -->
           <sec:authentication property="principal.authorities" var="authorities" />
           <c:forEach items="${authorities}" var="authority">
           
                <!-- Devido ao CaseSensitive do Map do Spring, a URL em letra maiuscula não funciona, então deixo tudo minúscula -->          
				<li><a href="${endereco}/${fn:toLowerCase(authority.authority)}">${authority.authority}</a></li>
		   </c:forEach>

          </ul>
                        
            
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"><sec:authentication property="principal.username" /><span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="${endereco}/logout">Sair</a></li>
                </ul>
              </li>
            </ul>             
            
            </sec:authorize>
            
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>