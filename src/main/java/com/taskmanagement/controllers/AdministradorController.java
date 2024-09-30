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

import com.taskmanagement.entities.Administrador;
import com.taskmanagement.entities.FuncionarioJunior;
import com.taskmanagement.entities.FuncionarioJuniorProjeto;
import com.taskmanagement.entities.FuncionarioSenior;
import com.taskmanagement.entities.FuncionarioSeniorProjeto;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.AdministradorRepository;
import com.taskmanagement.repositories.FuncionarioJuniorProjetoRepository;
import com.taskmanagement.repositories.FuncionarioJuniorRepository;
import com.taskmanagement.repositories.FuncionarioSeniorProjetoRepository;
import com.taskmanagement.repositories.FuncionarioSeniorRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@RestController
@RequestMapping(value="/administrador")
public class AdministradorController {
	
	private FuncionarioJuniorProjeto funcionarioJuniorProjeto = new FuncionarioJuniorProjeto();
	private FuncionarioSeniorProjeto funcionarioSeniorProjeto = new FuncionarioSeniorProjeto();
	private Projeto projeto = new Projeto();
	
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private FuncionarioJuniorRepository funcionarioJuniorRepository;
	
	@Autowired
	private FuncionarioJuniorProjetoRepository funcionarioJuniorProjetoRepository;
	
	@Autowired
	private FuncionarioSeniorRepository funcionarioSeniorRepository;
	
	@Autowired
	private FuncionarioSeniorProjetoRepository funcionarioSeniorProjetoRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	// GET
	
	@GetMapping(value="/funcionarios-juniores")
	public List<FuncionarioJunior> readFuncionariosJuniores(){
		return funcionarioJuniorRepository.findAll();
	}
	
	@GetMapping(value="/funcionarios-seniores")
	public List<FuncionarioSenior> readFuncionariosSeniores(){
		return funcionarioSeniorRepository.findAll();
	}
	
	@GetMapping(value="/administradores")
	public List<Administrador> readAdministrador(){
		return administradorRepository.findAll();
	}
	
	@GetMapping(value="/projetos")
	public Projeto listarProjetos(@PathVariable Long idProjeto) {
		
		return projetoRepository.findById(idProjeto).get();
	}
	
	@GetMapping(value="/tarefas")
	public List<Tarefa> readTarefas() {
		
		return tarefaRepository.findAll();
	}
	
	
	// POST
	
	@PostMapping(value="/cadastrar-funcionario-junior")
	public FuncionarioJunior cadastrarFuncionarioJunior(@RequestBody FuncionarioJunior funcionarioJunior) {
		return funcionarioJuniorRepository.save(funcionarioJunior);
	}
	
	@PostMapping(value="/cadastrar-funcionario-senior")
	public FuncionarioSenior cadastrarFuncionarioSenior(@RequestBody FuncionarioSenior funcionarioSenior) {
		return funcionarioSeniorRepository.save(funcionarioSenior);
	}
	
	@PostMapping(value="/criar-projeto")
	public Projeto criarProjeto(@RequestBody Projeto projeto) {
		return projetoRepository.save(projeto);
	}
	
	// Ver Quarta
	
	@PostMapping
	public void criarRelacao(Long idFuncionarioSenior) {
		// idProjeto
		funcionarioSeniorProjetoRepository.save(funcionarioSeniorProjeto);
	}

	// PUT
	
	// deixar null em senior e junior e false status
	@PutMapping(value="/deletar-projeto/{idProjeto}")
	public void updateProjeto(@PathVariable Long idProjeto) {
		projeto = projetoRepository.getById(idProjeto);
		projetoRepository.save(projeto);
	}
	
}
