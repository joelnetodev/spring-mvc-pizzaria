package com.joelnetodev.pizzaria.controllers;

import java.io.Console;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joelnetodev.pizzaria.dtos.PizzaDTO;
import com.joelnetodev.pizzaria.entities.enums.CategoriaPizzaEnum;
import com.joelnetodev.pizzaria.exceptions.BadRequestException;
import com.joelnetodev.pizzaria.services.IngredienteService;
import com.joelnetodev.pizzaria.services.PizzaService;

@Controller
@RequestMapping("/pizzas")
public class PizzaController 
{
	private final String nomeAtributoModelPizzas = "pizzas";
	
	@Autowired PizzaService _pizzaService;
	@Autowired IngredienteService _ingredienteService;
	
	@RequestMapping("/ola")
	@ResponseBody
	public String ola()
	{
		return "Hello Pizzas";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(Model model)
	{			
		model.addAttribute("titulo", "Listagem Pizzas");
		model.addAttribute(nomeAtributoModelPizzas, _pizzaService.consultarTodos());
		model.addAttribute("categoriasenum", CategoriaPizzaEnum.values());
		model.addAttribute("ingredientesLista", _ingredienteService.consultarTodos());
		
		return "pizzas/index";
	}
	
	@RequestMapping(value="/salvar/",method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String salvar(@RequestBody PizzaDTO pizzaDto, Model model) throws Exception
	{	
		try
		{
			_pizzaService.salvar(pizzaDto);
			
			model.addAttribute(nomeAtributoModelPizzas, _pizzaService.consultarTodos());
			return "pizzas/tabela";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable int id, Model model)
	{
		try
		{
			_pizzaService.deletar(id);
			
			model.addAttribute(nomeAtributoModelPizzas, _pizzaService.consultarTodos());
			return "pizzas/tabela";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/buscar/{id}")
	@ResponseBody
	public PizzaDTO buscar(@PathVariable int id)
	{
		try
		{
			return _pizzaService.consultarPorId(id);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
}
