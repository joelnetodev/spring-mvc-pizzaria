package com.joelnetodev.pizzaria.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joelnetodev.pizzaria.entities.Usuario;

import jdk.nashorn.internal.runtime.Context;

@Service
public class UsuarioService implements UserDetailsService {

	//UsuarioService implementa 'UserDetailsService' que possui o metodo 'loadUserByUsername' para implementar
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		//Este m�todo retorna um 'UserDetails' que � preenchido apenas com usu�rio e senha criptografada
		//Dessa maneira, quando retorna o usu�rio da base de dados, a autentica��o apenas verifica se
		//a senha do banco bate com a senha que foi passada pelo formul�rio e encriptada na configura��o
		return verificarUsuarioPorLogin(username);
	}
	
	private Usuario verificarUsuarioPorLogin(String nomeUsuario)
	{
		//Qualquer usu�rio com senha '123456' acessa a aplica��o
		//Pode ser substituido por uma consulta no banco de dados que retorne o Usuario do tipo 'UserDetails'
		
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUsuario);
		usuario.setSenha(getEncoder().encode("123456"));
		
		//Coloco as permiss�es para montar o menu da pizzaria
		//tamb�m poderia trazer isso do banco
		usuario.getPermissoes().add("Ingredientes");
		usuario.getPermissoes().add("Pizzas");
		
		return usuario;
	}
	
	//Retorna o encode usado para criptografar senha
	//(Esta aqui, pois unifica o lugar onde define o tipo de autentica��o)
	public BCryptPasswordEncoder getEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
	
