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

import com.joelnetodev.pizzaria.entities.Ingrediente;
import com.joelnetodev.pizzaria.entities.enums.CategoriaIngredienteEnum;
import com.joelnetodev.pizzaria.excptions.BadRequestException;
import com.joelnetodev.pizzaria.repositories.IIngredienteRepository;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController 
{
	private final String nomeAtributoModelIngredientes = "ingredientes";
	
	@Autowired IIngredienteRepository _ingredienteRepository;
	
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
		model.addAttribute(nomeAtributoModelIngredientes, _ingredienteRepository.findAll());
		model.addAttribute("categoriasenum", CategoriaIngredienteEnum.values());
		
		return "ingredientes/listagem";
	}
	
	@RequestMapping(value="/salvar/",method=RequestMethod.POST)
	public String salvar(@Valid @ModelAttribute Ingrediente ingrediente, Model model)
	{
		try
		{
			_ingredienteRepository.save(ingrediente);
			
			model.addAttribute(nomeAtributoModelIngredientes, _ingredienteRepository.findAll());
			return "ingredientes/tabela";
		}
		catch(Exception ex)
		{
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String deletar(@PathVariable int id, Model model)
	{
		try
		{
			_ingredienteRepository.delete(id);
			
			model.addAttribute(nomeAtributoModelIngredientes, _ingredienteRepository.findAll());
			return "ingredientes/tabela";
		}
		catch(Exception ex)
		{
			throw new BadRequestException();
		}
	}
	
	@RequestMapping("/buscar/{id}")
	@ResponseBody
	public Ingrediente buscar(@PathVariable int id)
	{
		try
		{
			Ingrediente ingrediente = _ingredienteRepository.findOne(id);
			return ingrediente;
		}
		catch(Exception ex)
		{
			throw new BadRequestException();
		}
	}
	
}
