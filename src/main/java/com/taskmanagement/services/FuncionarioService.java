package com.taskmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.taskmanagement.entities.Funcionario;
import com.taskmanagement.entities.FuncionarioProjeto;
import com.taskmanagement.entities.NivelPermissao;
import com.taskmanagement.entities.Prioridade;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Status;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioProjetoRepository;
import com.taskmanagement.repositories.FuncionarioRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionario_repository;
		
	@Autowired
	private TarefaRepository tarefa_repository;
	
	@Autowired
	private ProjetoRepository projeto_repository;
	
	@Autowired
	private FuncionarioProjetoRepository funcionario_projeto_repository;
	
	private Funcionario funcionario;
	private Projeto projeto;
	private Tarefa tarefa;
	private FuncionarioProjeto funcionario_projeto;
	private NivelPermissao nivel_permissao;
	private Status status;
	private Prioridade prioridade;
	
	public Funcionario pegarFuncionario(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario) {
				this.funcionario = funcionario;
				break;
			}
		}
		return this.funcionario;
	}
	
	public boolean criarProjeto(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean criarProjetoSucesso = true, existeFuncionarioSenior = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Senior) {
				this.funcionario = funcionario;
				existeFuncionarioSenior = true;
				break;
			}
		}
		
		if(existeFuncionarioSenior) {
			if(this.funcionario.getProjeto()==null) {
				return criarProjetoSucesso;
			}
			else {
				System.out.println("Funcionario Senior está em outro projeto");
				return criarProjetoSucesso = false;
			}
		}
		else {
			System.out.println("Funcionario Senior inexistente");
			return criarProjetoSucesso = false;
		}
	}
	
	public boolean encontrarAdministrador(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean cadastrarFuncionarioSucesso = true, existeAdministrador = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Administrador) {
				this.funcionario = funcionario;
				existeAdministrador = true;
				break;
			}
		}
		
		if(existeAdministrador) {
			return cadastrarFuncionarioSucesso;
		}
		else {
			System.out.println("Administrador inexistente");
			return cadastrarFuncionarioSucesso = false;
		}
	}
	
}
