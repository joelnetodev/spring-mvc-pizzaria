package com.joelnetodev.pizzaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joelnetodev.pizzaria.entities.Ingrediente;

@Repository
public interface IIngredienteRepository extends CrudRepository <Ingrediente, Integer>
{
	@Query(value="SELECT * FROM ingrediente i WHERE i.id in (:idsIngrediente)",nativeQuery=true)
    public List<Ingrediente> consultarPorIds(@Param("idsIngrediente") int[] idsIngrediente);
}
