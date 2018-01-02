package com.joelnetodev.pizzaria.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Entidade usuário que implementa 'UserDetails' para trabalhar com autenticação de usuário do Spring MVC
public class Usuario implements UserDetails 
{
	//Região de propriedades do usuário do 'UserDetails'
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for(String permissao : getPermissoes())
		{
			grantedAuthorities.add(new SimpleGrantedAuthority(permissao));
		}
		
		return grantedAuthorities;
	}

	public String getPassword() {
		return getSenha();
	}

	public String getUsername() {
		return getNome();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	//Região de propriedades do usuário customizado
	private String nome;
	private String senha;
	private List<String> permissoes;
	
	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario() 
	{
		permissoes = new ArrayList<String>();
	}
	
}
