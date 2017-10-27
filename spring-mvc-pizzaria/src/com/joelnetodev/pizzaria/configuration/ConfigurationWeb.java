package com.joelnetodev.pizzaria.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

//Informa ao Spring quais pacotes deve escanear as classes de configuração
//Ex: Se a classe estiver com a notation, ele disponibiliza no container
@EnableWebMvc
@Configuration
@ComponentScan({ "com.joelnetodev.pizzaria" })
public class ConfigurationWeb extends WebMvcConfigurerAdapter 
{
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) 
	{
		//Por padrão as páginas ficam dentro de webapp
		UrlBasedViewResolver urlResolver = new UrlBasedViewResolver();
		urlResolver.setPrefix("/WEB-INF/Views/");
		urlResolver.setSuffix(".jsp");
		urlResolver.setViewClass(JstlView.class);
		registry.viewResolver(urlResolver);
	}
	
	//Resources onde ficam os css, jquery e imagens
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }
	
	@Override
	public void addViewControllers (ViewControllerRegistry registry) 
	{
		//Adiciono um controller para o endereço '/' que retorna a view indext dentro de inicio
		registry.addViewController("/").setViewName("inicio/index");
	}
}