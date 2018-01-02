package com.joelnetodev.pizzaria.dtos;

import com.joelnetodev.pizzaria.entities.enums.CategoriaIngredienteEnum;

//Essa classe representa um ingrediente do banco, ela foi criada para ser um Model do MVC 
//e para evitar que transporte a entidade Ingrediente em cada requisição.
public class IngredienteDTO
{

	private int Id;
	

	private String Nome;
	

	private CategoriaIngredienteEnum Categoria;

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public CategoriaIngredienteEnum getCategoria() {
		return Categoria;
	}

	public void setCategoria(CategoriaIngredienteEnum categoria) {
		this.Categoria = categoria;
	}
}