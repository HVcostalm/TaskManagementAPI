package com.taskmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.entities.FuncionarioJunior;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioJuniorRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@Service
public class FuncionarioJuniorService {
	
	
	
	@Autowired
	private FuncionarioJuniorRepository funcionarioJuniorRepository;
	
	private FuncionarioJunior funcionarioJunior = new FuncionarioJunior();
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	private Tarefa tarefa = new Tarefa();
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	private Projeto projeto = new Projeto();
	
	public void verProjeto(Long idProjeto) {
		
	}
	
	// Esperar Quarta Para Ver Sobre Isso
	/*
	public FuncionarioJunior atualizarFuncionario(Long idFuncionarioJunior) {
		funcionarioJunior = funcionarioJuniorRepository.findById(idFuncionarioJunior).get();
		
	}
	
	public Tarefa atualizarTarefa(Long idTarefa){
		
	}
	*/
}
