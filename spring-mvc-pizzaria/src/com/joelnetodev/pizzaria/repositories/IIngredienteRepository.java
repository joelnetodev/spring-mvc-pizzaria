package com.joelnetodev.pizzaria.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joelnetodev.pizzaria.entities.Ingrediente;

@Repository
public interface IIngredienteRepository extends CrudRepository <Ingrediente, Integer>
{

}
