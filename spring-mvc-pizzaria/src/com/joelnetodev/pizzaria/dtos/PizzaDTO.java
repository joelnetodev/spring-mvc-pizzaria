package com.joelnetodev.pizzaria.dtos;

import java.util.ArrayList;
import java.util.List;

import com.joelnetodev.pizzaria.entities.enums.CategoriaPizzaEnum;

//Essa classe representa uma pizza do banco, ela foi criada para ser um Model do MVC 
//e para evitar que transporte a entidade Ingrediente em cada requisição.
public class PizzaDTO
{
	private int Id;
	private String Nome;
	private double Preco;
	private CategoriaPizzaEnum Categoria;

	private List<IngredienteDTO> Ingredientes;
	
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
	
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		this.Preco = preco;
	}

	
	public CategoriaPizzaEnum getCategoria() {
		return Categoria;
	}
	public void setCategoria(CategoriaPizzaEnum categoria) {
		this.Categoria = categoria;
	}


	public List<IngredienteDTO> getIngredientes() {
		return Ingredientes;
	}
	public void setIngredientes(List<IngredienteDTO> ingredientes) {
		this.Ingredientes = ingredientes;
	}
	
	public PizzaDTO()
	{
		Ingredientes = new ArrayList<IngredienteDTO>();
	}
}