package com.joelnetodev.pizzaria.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joelnetodev.pizzaria.entities.Pizza;

@Repository
public interface IPizzaRepository extends CrudRepository <Pizza, Integer>
{
	 
}
