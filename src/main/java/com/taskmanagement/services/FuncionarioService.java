package com.taskmanagement.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.entities.Funcionario;
import com.taskmanagement.entities.FuncionarioProjeto;
import com.taskmanagement.entities.NivelPermissao;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Status;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioProjetoRepository;
import com.taskmanagement.repositories.FuncionarioRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

import jakarta.transaction.Transactional;

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
	private NivelPermissao nivel_permissao;
	private Status status;
	private LocalDate data = LocalDate.now();
	
	public Funcionario encontrarFuncionario(Long id_funcionario) {
		return this.funcionario = funcionario_repository.findById(id_funcionario).get();
	}
	
	public List<Funcionario> listarFuncionariosAtivos(){
		return funcionario_repository.findByStatusTrue();
	}
	
	public boolean criarProjeto(Long id_funcionario) {
		boolean existeFuncionarioSenior;
		
		existeFuncionarioSenior = this.encontrarSenior(id_funcionario);
		
		if(existeFuncionarioSenior) {
			if(this.funcionario.getProjeto()==null) {
				return true;
			}
			else {
				System.out.println("Funcionario Senior está em outro projeto");
				return false;
			}
		}
		else {
			System.out.println("Funcionario Senior inexistente");
			return false;
		}
	}
	
	public boolean encontrarFuncionarioPorNivelPermissao(Long id_funcionario, NivelPermissao nivel) {
	    List<Funcionario> funcionarios = funcionario_repository.findAll();
	    
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario()==id_funcionario && funcionario.getNivel_permissao()==nivel && funcionario.isStatus()==true) {
				return true;
			}
		}
		return false;
	}
	
	public List<Funcionario> listarFuncionariosPorNivelPermissao(NivelPermissao nivel){
		List<Funcionario> funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosNivelPermissao = new ArrayList<>();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getNivel_permissao()==nivel) {
				funcionariosNivelPermissao.add(funcionario);
			}
		}
		
		return funcionariosNivelPermissao;
	}
	
	public boolean encontrarAdministrador(Long id_funcionario) {
		boolean existe = this.encontrarFuncionarioPorNivelPermissao(id_funcionario, NivelPermissao.Administrador);

		if (!existe) {
			System.out.println("Administrador inexistente ou demitido");
		}

		return existe;
	}
	
	public List<Funcionario> listarAdministradores(){
		return this.listarFuncionariosPorNivelPermissao(NivelPermissao.Administrador);
	}
	
	public boolean encontrarSenior(Long id_funcionario) {
		boolean existe = this.encontrarFuncionarioPorNivelPermissao(id_funcionario, NivelPermissao.Senior);

		if (!existe) {
			System.out.println("Senior inexistente ou demitido");
		}

		return existe;
	}
	
	public List<Funcionario> listarSeniores(){
		return this.listarFuncionariosPorNivelPermissao(NivelPermissao.Senior);
	}
	
	public boolean encontrarJunior(Long id_funcionario) {
		boolean existe = this.encontrarFuncionarioPorNivelPermissao(id_funcionario, NivelPermissao.Junior);

		if (!existe) {
			System.out.println("Junior inexistente ou demitido");
		}

		return existe;
	}
	
	public List<Funcionario> listarJuniores(){
		return this.listarFuncionariosPorNivelPermissao(NivelPermissao.Junior);
	}
	
	public boolean verificarExisteProjeto(Long id_projeto) {
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		
		for(Projeto projeto: projetos) {
			if(projeto.getId_projeto()==id_projeto) {
				return true;
			}
		}
			return false;
	}
	
	public boolean verificarSeniorProjeto(Long id_funcionario, Long id_projeto) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		if(this.funcionario.getProjeto()==this.projeto)
			return true;
		else
			return false;
	}
	
	public boolean verificarFuncionarioSemProjeto(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		
		if(this.funcionario.getProjeto()==null ) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	public boolean verificarFuncionarioProjetoDemicao(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		
		for(Projeto projeto: projetos) {
			if(this.funcionario.getProjeto()==projeto) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verificarQuantidadeFuncionariosProjeto(Long id_projeto) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosProjeto = new ArrayList<>();
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getProjeto()==this.projeto)
				funcionariosProjeto.add(funcionario);
		}
		
		if(funcionariosProjeto.size()<7)
			return true;
		
		return false;
	}
	
	public boolean encontrarTarefa(Long id_tarefa) {
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefa_repository.findAll();
		
		for(Tarefa tarefa: tarefas) {
			if(tarefa.getId_tarefa()==id_tarefa) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<Tarefa> pegarTodasTarefasProjeto(Projeto projeto, List<Tarefa> tarefasProjeto){
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefa_repository.findAll();
		
		for(Tarefa tarefa: tarefas) {
			if(tarefa.getProjeto()==projeto) {
				tarefasProjeto.add(tarefa);
			}
		}
		
		return tarefasProjeto;
	}
	
	public boolean verificarTodasTarefasConcluidas(Projeto projeto) {
		List<Tarefa> tarefasProjeto = new ArrayList<>();
		int contadorTarefasConcluidas = 0;
		
		tarefasProjeto = this.pegarTodasTarefasProjeto(projeto, tarefasProjeto);
		
		for(Tarefa tarefa: tarefasProjeto) {
			if(tarefa.getStatus()==status.Concluido) {
				contadorTarefasConcluidas++;
			}
		}
		
		if(tarefasProjeto.size()==contadorTarefasConcluidas) {
			return true;
		}
		else
			return false;
	}
	
	@Transactional
	public void finalizarProjetoFuncionarios(Projeto projeto) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<FuncionarioProjeto> funcionariosProjetos = new ArrayList<>();
		funcionariosProjetos = funcionario_projeto_repository.findAll();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getProjeto()==projeto) {
				funcionario.setProjeto(null);
				funcionario_repository.save(funcionario);
			}
		}
		
		for(FuncionarioProjeto funcionarioProjeto: funcionariosProjetos) {
			if(funcionarioProjeto.getProjeto()==projeto) {
				funcionarioProjeto.setData_participacao_final(data);
				funcionario_projeto_repository.save(funcionarioProjeto);
			}
		}
		
	}
	
	public boolean verificarSomenteMudancaSenhaEmailAlterados(Funcionario funcionario, Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		
		if( (this.funcionario.getProjeto()==funcionario.getProjeto()) && (this.funcionario.getNivel_permissao()==funcionario.getNivel_permissao()) && (this.funcionario.getLogin_funcionario().equalsIgnoreCase(funcionario.getLogin_funcionario())) && (this.funcionario.getId_funcionario()==funcionario.getId_funcionario())  ) {
			return true;
		}
		
		return false;
	}
	
	public boolean verificarSomenteMudancaNomeDescricaoPrioridadeAlterada(Tarefa tarefa, Long id_tarefa) {
		this.tarefa = tarefa_repository.findById(id_tarefa).get();
		
		if(this.tarefa.getProjeto().getId_projeto()==tarefa.getProjeto().getId_projeto() && this.tarefa.getStatus()==tarefa.getStatus() && this.tarefa.getId_tarefa()==tarefa.getId_tarefa()) {
			return true;
		}
		
		return false;
	}
	
	public boolean verificarSomenteMudancaNomeDescricao(Projeto projeto, Long id_projeto){
		this.projeto = projeto_repository.findById(id_projeto).get();
				
		if(this.projeto.getId_projeto()==projeto.getId_projeto() && this.projeto.getData_prevista_entrega().isEqual(projeto.getData_prevista_entrega()) && (this.projeto.getData_conclusao()==null && projeto.getData_conclusao()==null) && this.projeto.isStatus()==projeto.isStatus()) {
			return true;
		}
		return false;
	}
	
	public List<Tarefa> listarTarefasProjeto(Projeto projeto){
		List<Tarefa> tarefasProjeto = new ArrayList<>();
		
		tarefasProjeto = this.pegarTodasTarefasProjeto(projeto, tarefasProjeto);
		
		if(tarefasProjeto.isEmpty()) {
			System.out.println("Nenhuma tarefa foi criada");
		}
		
		return tarefasProjeto;
	}
	
	
	public Projeto exibirProjetoFuncionario(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		
		for(Projeto projeto: projetos) {
			if(this.funcionario.getProjeto()==projeto) {
				return projeto;
			}
		}
		System.out.println("Este funcionario não tem um projeto atribuido");
		return null;
	}
	
	public List<Projeto> mostrarProjetosAtivos(){
		List<Projeto> projetos = projeto_repository.findAll(); 
		List<Projeto> projetosAtivos = new ArrayList<>();
		
		for(Projeto projeto: projetos) {
			if(projeto.isStatus()==true) {
				projetosAtivos.add(projeto);
			}
		}
		
		if(projetosAtivos.isEmpty()) {
			System.out.println("Nenhum projeto ativo");
			return null;
		}
		else
			return projetosAtivos;		
	}	
}
