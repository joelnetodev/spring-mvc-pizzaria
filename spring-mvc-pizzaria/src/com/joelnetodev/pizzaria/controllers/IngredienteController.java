package com.joelnetodev.pizzaria.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joelnetodev.pizzaria.dtos.IngredienteDTO;
import com.joelnetodev.pizzaria.entities.enums.CategoriaIngredienteEnum;
import com.joelnetodev.pizzaria.services.IngredienteService;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/ingredientes")
public class IngredienteController 
{
	private final String nomeAtributoModelIngredientes = "ingredientes";
	
	//Autowired é a IoC automatica pelo spring
	@Autowired IngredienteService _ingredienteService;
	
	//ResponseBody quando retorno é em string, JSON
	@RequestMapping("/ola")
	@ResponseBody
	public String ola()
	{
		return "Hello Ingrediente";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(Model model)
	{	
		model.addAttribute("titulo", "Listagem Ingredientes");
		model.addAttribute(nomeAtributoModelIngredientes, _ingredienteService.consultarTodos());
		model.addAttribute("categoriasenum", CategoriaIngredienteEnum.values());
		
		return "ingredientes/index";
	}
	
	@RequestMapping(value="/salvar/",method=RequestMethod.POST)
	public String salvar(IngredienteDTO ingredienteDto, Model model) throws Exception
	{
		try
		{
			_ingredienteService.salvar(ingredienteDto);
			
			model.addAttribute(nomeAtributoModelIngredientes, _ingredienteService.consultarTodos());
			return "ingredientes/tabela";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw ex;
		}
	}
	
	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable int id, Model model) throws Exception
	{
		try
		{
			_ingredienteService.deletar(id);
			
			model.addAttribute(nomeAtributoModelIngredientes, _ingredienteService.consultarTodos());
			return "ingredientes/tabela";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw ex;
		}
	}
	
	@RequestMapping("/buscar/{id}")
	@ResponseBody
	public IngredienteDTO buscar(@PathVariable int id) throws Exception
	{
		try
		{
			return _ingredienteService.consultarPorId(id);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw ex;
		}
	}
	
}
