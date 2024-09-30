package com.taskmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entities.FuncionarioJunior;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioJuniorRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@RestController
@RequestMapping(value="/funcionario-junior")
public class FuncionarioJuniorController {
	
	@Autowired
	private FuncionarioJuniorRepository funcionarioJuniorRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	
	// GET
	
	// Ver isso tudo abaixo quarta
	
	@GetMapping(value="/{idProjeto}")
	public Projeto readProjeto(@PathVariable Long idProjeto) {
		
		return projetoRepository.findById(idProjeto).get();
	}
	
	@GetMapping(value="/tarefas")
	public List<Tarefa> readTarefas() {
		
		return tarefaRepository.findAll();
	}
	
	@GetMapping(value="/{idTarefa}")
	public Tarefa readTarefa(@PathVariable Long idTarefa) {
		
		return tarefaRepository.findById(idTarefa).get();
	}
	
	// PUT
	
	// senha e email
	@PutMapping(value="/{idFuncionarioJunior}")
	public FuncionarioJunior updateFuncionarioJunior(@RequestBody FuncionarioJunior funcionarioJunior) {
		return funcionarioJuniorRepository.save(funcionarioJunior);
	}
	
	@PutMapping(value="/tarefa/{idTarefa}")
	public Tarefa updateTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
}
