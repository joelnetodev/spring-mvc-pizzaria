package com.joelnetodev.pizzaria.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joelnetodev.pizzaria.dtos.IngredienteDTO;
import com.joelnetodev.pizzaria.entities.Ingrediente;
import com.joelnetodev.pizzaria.entities.enums.CategoriaIngredienteEnum;
import com.joelnetodev.pizzaria.excptions.BadRequestException;
import com.joelnetodev.pizzaria.repositories.IIngredienteRepository;
import com.joelnetodev.pizzaria.services.IngredienteService;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController 
{
	private final String nomeAtributoModelIngredientes = "ingredientes";
	
	@Autowired IngredienteService _ingredienteService;
	
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
	public String salvar(@Valid @ModelAttribute IngredienteDTO ingredienteDto, Model model)
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
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable int id, Model model)
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
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/buscar/{id}")
	@ResponseBody
	public IngredienteDTO buscar(@PathVariable int id)
	{
		try
		{
			return _ingredienteService.consultarPorId(id);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
	
}
