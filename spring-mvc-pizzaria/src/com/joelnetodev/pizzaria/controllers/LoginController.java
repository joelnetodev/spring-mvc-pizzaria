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
@RequestMapping("/login")
public class LoginController 
{	
	@RequestMapping(method=RequestMethod.GET)
	public String login(Model model)
	{			
		model.addAttribute("titulo", "Login");
		return "login/index";
	}
	
	@RequestMapping(value="/acessar", method=RequestMethod.POST)
	public String acessar(Model model) throws Exception
	{	
		try
		{
			System.out.println("tentou acessar");
			return "inicio/index";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
	
	@RequestMapping(value="/sair")
	public String sair(Model model) throws Exception
	{	
		try
		{
			model.addAttribute("titulo", "Login");
			return "login/index";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			throw new BadRequestException();
		}
	}
}
