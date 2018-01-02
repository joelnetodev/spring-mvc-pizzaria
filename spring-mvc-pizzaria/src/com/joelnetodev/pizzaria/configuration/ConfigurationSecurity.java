package com.joelnetodev.pizzaria.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.joelnetodev.pizzaria.services.PizzaService;
import com.joelnetodev.pizzaria.services.UsuarioService;


@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter 
{   
	//Autowired é a IoC automatica pelo spring container
	@Autowired UsuarioService _usuarioService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	//Disabilita verificação pelo csrf token para autorização de POST
    	http.csrf().disable();
    	
    	//Tem que dizer que permite login e resources, que é onde ficam as imagens, css e js
    	http.authorizeRequests()
        .antMatchers("/login/**", "/resources/**").permitAll()
        .anyRequest().authenticated();
    	
    	//configuração de pagina de login e funcionamento
    	http
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?erro")
        .defaultSuccessUrl("/inicio")
        .permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//Acessa se entrar esses usuários (pode ser feito de outro jeito, via banco)
        //auth
            //.inMemoryAuthentication()
            //.withUser("joel").password("joel").roles("USER");
    	
    	//Usa a autenticação do serviço que implementa um 'UserDetailsService'
    	//Este mesmo serviço fornece a classe que encripta a senha do usuário passada pelo formulário
    	//(caso contrario, a senha não seria encriptada, mas não tem problema configurar sem encriptar)
    	auth.userDetailsService(_usuarioService)
    	.passwordEncoder(_usuarioService.getEncoder());
    }
}


