package com.joelnetodev.pizzaria.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.NotNull;

import com.joelnetodev.pizzaria.entities.enums.CategoriaPizzaEnum;

//Entidade pizza do banco de dados
//os atributos são do Hibernate para mapear tabelas e propriedades
@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@NotNull
	private String Nome;

	@NotNull
	private Double Preco;

	@NotNull
	private CategoriaPizzaEnum Categoria;

	//One to many para mapear e FetchType.EAGER para lazy load
	//O mapeamento é de 3 tabelas: pizza, ingrediente e pizza_ingrediente
	//Não existem 3 entidades, so existem 2, então faz-se o mapeia inverso para o retono ser da entidade ingrediente
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="pizza_ingrediente",
            joinColumns = @JoinColumn( name="pizza_id"),
            inverseJoinColumns = @JoinColumn( name="ingrediente_id")
    )
	private List<Ingrediente> Ingredientes;


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

	public Double getPreco() {
		return Preco;
	}

	public void setPreco(Double preco) {
		this.Preco = preco;
	}

	public CategoriaPizzaEnum getCategoria() {
		return Categoria;
	}

	public void setCategoria(CategoriaPizzaEnum categoria) {
		this.Categoria = categoria;
	}

	public List<Ingrediente> getIngredientes() {
		return Ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.Ingredientes = ingredientes;
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
		Pizza other = (Pizza) obj;
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
