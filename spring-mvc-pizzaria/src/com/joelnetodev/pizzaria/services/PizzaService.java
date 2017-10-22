package com.joelnetodev.pizzaria.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joelnetodev.pizzaria.dtos.PizzaDTO;
import com.joelnetodev.pizzaria.entities.Pizza;
import com.joelnetodev.pizzaria.repositories.IIngredienteRepository;
import com.joelnetodev.pizzaria.repositories.IPizzaRepository;

@Service
public class PizzaService
{
	@Autowired IIngredienteRepository _ingredienteRepository;
	@Autowired IPizzaRepository _pizzaRepository;
	
	public PizzaDTO consultarPorId(int id)
	{
		Pizza pizza = _pizzaRepository.findOne(id);
		
		return converterParaPizzaDto(pizza);
	}
	
	public List<PizzaDTO> consultarTodos()
	{
		_ingredienteRepository.consultarPorIds(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
		
		Iterable<Pizza> pizzas = _pizzaRepository.findAll();
		
		return converterParaPizzasDto(pizzas);
	}
	
	public void deletar(int id)
	{
		_pizzaRepository.delete(id);
	}
	
	public void salvar(PizzaDTO pizzaDto)
	{
		Pizza pizza = converterParaPizza(pizzaDto);
		
		_pizzaRepository.save(pizza);
	}
	
	private List<PizzaDTO> converterParaPizzasDto(Iterable<Pizza> pizzas)
	{
		List<PizzaDTO> pizzasDto = new ArrayList<PizzaDTO>();
		for (Pizza pizza : pizzas) 
		{
			pizzasDto.add(converterParaPizzaDto(pizza));
		}
		
		return pizzasDto;
	}
	
	private PizzaDTO converterParaPizzaDto(Pizza pizza)
	{
		PizzaDTO pizzaDto = new PizzaDTO();
		
		pizzaDto.setId(pizza.getId());
		pizzaDto.setNome(pizza.getNome());
		pizzaDto.setPreco(pizza.getPreco());
		pizzaDto.setCategoria(pizza.getCategoria());
		
		pizzaDto.setIngredientes(IngredienteService.converterParaIngredientesDto(pizza.getIngredientes()));
				
		return pizzaDto;
	}
	
	private Pizza converterParaPizza(PizzaDTO pizzaDto)
	{
		Pizza pizza = new Pizza();
		
		pizza.setId(pizzaDto.getId());
		pizza.setNome(pizzaDto.getNome());
		pizza.setPreco(pizzaDto.getPreco());
		pizza.setCategoria(pizzaDto.getCategoria());
		
		pizza.setIngredientes(IngredienteService.converterParaIngredientes(pizzaDto.getIngredientes()));
				
		return pizza;
	}
}
