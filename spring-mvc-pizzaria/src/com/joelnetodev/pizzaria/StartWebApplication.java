package com.joelnetodev.pizzaria;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class StartWebApplication implements WebApplicationInitializer 
{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException 
	{
		AnnotationConfigWebApplicationContext webAppContext = createWebApplicationContext();
				
		//Spring MVC - Front Controler - Dispatcher, redireciona as requisi��es para o controlador
		//Recebe parametro o contexto, para reconehcer os objetos e classes
		Dynamic dynamicAppServlet = servletContext.addServlet("appServlet", new DispatcherServlet(webAppContext));
		dynamicAppServlet.setLoadOnStartup(1);
		
		//mapp de URL - Controllers
		//Se colocar /app/ o endere�o fica = localhost:porta/pizzaria/app/...
		//o /pizzaria/ esta no jetty em pom.xml
		dynamicAppServlet.addMapping("/");
		
		//Auxilia na cria��o das classes spring
		//EX: Criar instancia de controllador se n�o houver e se precisar
		//servletContext.addListener(new ContextLoaderListener(webAppContext));
	}
	
	private AnnotationConfigWebApplicationContext createWebApplicationContext()
	{
		//Contexto que gerencia os objetos e classes do projeto
		//Caminho de onde est�o as classes de configura��o
		AnnotationConfigWebApplicationContext webappContext = new AnnotationConfigWebApplicationContext();
		webappContext.setConfigLocation("com.joelnetodev.pizzaria.configuration");
		
		return webappContext;
	}
}