package com.joelnetodev.pizzaria.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joelnetodev.pizzaria.dtos.IngredienteDTO;
import com.joelnetodev.pizzaria.entities.Ingrediente;
import com.joelnetodev.pizzaria.repositories.IIngredienteRepository;

@Service
public class IngredienteService
{
	@Autowired IIngredienteRepository _ingredienteRepository;
	
	public IngredienteDTO consultarPorId(int id)
	{
		Ingrediente ingrediente = _ingredienteRepository.findOne(id);
		
		return converterParaIngredienteDto(ingrediente);
	}
	
	public List<IngredienteDTO> consultarTodos()
	{
		Iterable<Ingrediente> ingrediente = _ingredienteRepository.findAll();
		
		return converterParaIngredientesDto(ingrediente);
	}
	
	public void deletar(int id)
	{
		_ingredienteRepository.delete(id);
	}
	
	public void salvar(IngredienteDTO ingredienteDto)
	{
		Ingrediente ingrediente = converterParaIngrediente(ingredienteDto);
		
		_ingredienteRepository.save(ingrediente);
	}
	
	public static List<IngredienteDTO> converterParaIngredientesDto(Iterable<Ingrediente> ingredientes)
	{
		List<IngredienteDTO> ingredientesDtos = new ArrayList<IngredienteDTO>();
		for (Ingrediente ingrediente : ingredientes) 
		{
			ingredientesDtos.add(converterParaIngredienteDto(ingrediente));
		}
		
		return ingredientesDtos;
	}
	
	private static IngredienteDTO converterParaIngredienteDto(Ingrediente ingrediente)
	{
		IngredienteDTO ingredienteDto = new IngredienteDTO();
		
		ingredienteDto.setId(ingrediente.getId());
		ingredienteDto.setNome(ingrediente.getNome());
		ingredienteDto.setCategoria(ingrediente.getCategoria());
		
		return ingredienteDto;
	}
	
	private Ingrediente converterParaIngrediente(IngredienteDTO ingredienteDto)
	{
		Ingrediente ingrediente = new Ingrediente();
		
		if(ingredienteDto.getId() != 0)
			ingrediente.setId(ingredienteDto.getId());
				
		ingrediente.setNome(ingredienteDto.getNome());
		ingrediente.setCategoria(ingredienteDto.getCategoria());
		
		return ingrediente;
	}
	
	private List<Ingrediente> converterParaIngredientes(List<IngredienteDTO> ingredientesDto)
	{
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		
		for (IngredienteDTO ingredienteDto : ingredientesDto) 
		{
			ingredientes.add(converterParaIngrediente(ingredienteDto));
		}
		
		return ingredientes;
	}
}
