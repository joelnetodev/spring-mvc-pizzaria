package com.joelnetodev.pizzaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joelnetodev.pizzaria.entities.Ingrediente;

@Repository
public interface IIngredienteRepository extends CrudRepository <Ingrediente, Integer>
{
	//@Query("select i.* from ingrediente i inner join pizza_ingrediente p on p.ingrediente_id = i.id and p.pizza_id = :idPizza")
	//Iterable<Ingrediente> consultarPorPizza(int idPizza);
}
