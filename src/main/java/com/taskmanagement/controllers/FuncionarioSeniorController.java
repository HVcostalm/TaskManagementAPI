package com.taskmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.taskmanagement.entities.FuncionarioJunior;
import com.taskmanagement.entities.FuncionarioJuniorProjeto;
import com.taskmanagement.entities.FuncionarioSenior;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioJuniorProjetoRepository;
import com.taskmanagement.repositories.FuncionarioJuniorRepository;
import com.taskmanagement.repositories.FuncionarioSeniorRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@RestController
@RequestMapping(value="/funcionario-senior")
public class FuncionarioSeniorController {
	
	private FuncionarioJuniorProjeto funcionarioJuniorProjeto = new FuncionarioJuniorProjeto();
	private Projeto projeto = new Projeto();
	
	@Autowired
	private FuncionarioJuniorRepository funcionarioJuniorRepository;
	
	@Autowired
	private FuncionarioJuniorProjetoRepository funcionarioJuniorProjetoRepository;
	
	@Autowired
	private FuncionarioSeniorRepository funcionarioSeniorRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	// GET
	
	@GetMapping(value="/{idProjeto}")
	public Projeto readProjeto(@PathVariable Long idProjeto) {
		
		return projetoRepository.findById(idProjeto).get();
	}
	
	@GetMapping(value="/tarefas")
	public List<Tarefa> readTarefas() {
		
		return tarefaRepository.findAll();
	}
	
	@GetMapping(value="/tarefa/{idTarefa}")
	public Tarefa readTarefa(@PathVariable Long idTarefa) {
		
		return tarefaRepository.findById(idTarefa).get();
	}
	
	// POST
	
	@PostMapping(value="/tarefa")
	public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	// Ver Quarta
	
	@PostMapping
	public void criarRelacao(Long idFuncionarioJunior) {
		// idProjeto
		funcionarioJuniorProjetoRepository.save(funcionarioJuniorProjeto);
	}

	// PUT
	
	@PutMapping(value="/{idFuncionarioSenior}")
	public FuncionarioSenior updateFuncionarioSenior(@RequestBody FuncionarioSenior funcionarioSenior) {
		return funcionarioJuniorRepository.save(funcionarioSenior);
	}
	
	// projeto
	@PutMapping(value="/funcionario-junior/{idFuncionarioJunior}")
	public FuncionarioJunior updateFuncionario(@RequestBody FuncionarioJunior funcionarioJunior) {
		return funcionarioJuniorRepository.save(funcionarioJunior);
	}
	
	@PutMapping(value="/tarefa/{idTarefa}")
	public Tarefa updateTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	
	// deixar null em senior e junior e false status
	@PutMapping(value="/finalizar-projeto/{idProjeto}")
	public void finalizarProjeto(@PathVariable Long idProjeto) {
		projeto = projetoRepository.getById(idProjeto);
		projetoRepository.save(projeto);
	}
	
	// DELETE
	
	@DeleteMapping(value="/tarefa/{idTarefa}")
	public void deleteTarefa(@PathVariable Long idTarefa) {
		tarefaRepository.deleteById(idTarefa);
	}
	
}
