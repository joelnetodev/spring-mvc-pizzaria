package com.joelnetodev.pizzaria.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.joelnetodev.pizzaria.entities.enums.CategoriaIngredienteEnum;

//Entidade ingrediente do banco de dados
//os atributos são do Hibernate para mapear tabelas e propriedades
@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@NotNull
	private String Nome;
	
	@NotNull
	private CategoriaIngredienteEnum Categoria;

	@OneToMany
	@JoinTable(
            name="pizza_ingrediente",
            joinColumns = @JoinColumn( name="ingrediente_id"),
            inverseJoinColumns = @JoinColumn( name="pizza_id")
    )
	private List<Pizza> Pizzas;
	
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
	
	public List<Pizza> getPizzas() {
		return Pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.Pizzas = pizzas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Categoria == null) ? 0 : Categoria.hashCode());
		result = prime * result + Id;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (Categoria != other.Categoria)
			return false;
		if (Id != other.Id)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}

	
	
}