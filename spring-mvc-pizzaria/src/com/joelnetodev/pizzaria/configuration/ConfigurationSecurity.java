package com.joelnetodev.pizzaria.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;


@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter 
{   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//Tem que dizer que permite login e resources, que é onde ficam as imagens, css e js
    	http.authorizeRequests()
        .antMatchers("/login/**", "/resources/**").permitAll()
        .anyRequest().authenticated()
        	.and()
        	.formLogin()
        	.loginPage("/login")
        	.permitAll()
        		.and()
        		.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("joel").password("joel").roles("USER");
    }
}


