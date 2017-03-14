package com.joelnetodev.pizzaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joelnetodev.pizzaria.repositories.IPizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController 
{
	@Autowired IPizzaRepository _pizzaRepository;
	
	@RequestMapping("/ola/{nomequalquer}")
	@ResponseBody
	public String ola(@PathVariable("nomequalquer") String nome)
	{
		return "Hellow Pizza " + nome;
	}
}
